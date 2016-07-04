package com.bigapps.doga.dovmeler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by shadyfade on 01.03.2016.
 */
public class DovmeDetay extends Activity{ // Seçilen dövmenin detayları
    private static String link;
    private String DovmeYolu;
    private boolean kaydetmeflag=true;
    private InterstitialAd mInterstitialAd;
    private Button indir_btn,paylas_btn;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dovmedetay_layout);

        // MobileAds.initialize(getApplicationContext(), "ca-app-pub-6164922138244802/8584540570");

        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6164922138244802/2566529772");

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.gecis_ad_unit_id));

        indir_btn = (Button) findViewById(R.id.indir_btn);
        paylas_btn = (Button) findViewById(R.id.paylas_btn);

        if(isNetworkAvailable())
            loadGecisReklam();

        mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        flag=true;
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        flag=true;
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        showGecisReklam();
                    }
        });

        String id = getIntent().getExtras().getString("id");
        link = getIntent().getExtras().getString("link");

        SquaredImageView view = (SquaredImageView) findViewById(R.id.dovme_iv);

        Picasso.with(getApplicationContext()) //
                .load(link) //
                .placeholder(R.drawable.progress_animation) //
                .error(R.drawable.error) //
                .fit() //
                .tag(getApplicationContext()) //
                .into(view);


        paylas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    shareImage();
                }

            }
        });

        indir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    imageDownload(getApplicationContext(), link);
                }

            }
        });
    }

    public void loadGecisReklam() {
        // reklam yüklenene kadar reklamGoster butonunu disable ediyoruz
        indir_btn.setEnabled(false);
        paylas_btn.setEnabled(false);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("0F2A8B170C4A2288C0C1163012BD9176")
                .build();

        //Reklam Yükleniyor
        mInterstitialAd.loadAd(adRequest);
    }

    public void showGecisReklam() {
        // Tekrar reklam yüklenene kadar disable edilecek
        indir_btn.setEnabled(true);
        paylas_btn.setEnabled(true);

        if (mInterstitialAd.isLoaded()) {//Eğer reklam yüklenmişse kontrol ediliyor
            mInterstitialAd.show(); //Reklam yüklenmişsse gösterilecek
        } else
        {
            Log.w("dovmeler", "Reklam Yüklenemedi!");
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void shareImage() {
        final ImageView imgview= (ImageView)findViewById(R.id.dovme_iv);

        Uri bmpUri = getLocalBitmapUri(imgview);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            // Launch sharing dialog for image
            startActivity(Intent.createChooser(shareIntent, "Dövmeni Paylaş!"));
        } else {
            Toast.makeText(this, "Paylaşılamadı!", Toast.LENGTH_SHORT).show();
        }
    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(DovmeYolu);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void imageDownload(Context ctx, String url){
        Picasso.with(ctx)
                .load(link)
                .into(getTarget(url));
        if(kaydetmeflag) {
            Toast.makeText(this, "İndirildi!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "İndirirken Hata Oluştu!", Toast.LENGTH_SHORT).show();
    }

    //target to save
    private Target getTarget(final String url){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File klasor = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Download");

                        boolean success = true;
                        if (!klasor.exists()) {
                            success = klasor.mkdir();
                        }
                        if (success) {
                            File file = new File(klasor.getPath() + "/" + random() + ".jpg");
                            DovmeYolu = file.getPath();
                            try {
                                file.createNewFile();
                                FileOutputStream ostream = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                                ostream.flush();
                                ostream.close();
                                galleryAddPic();
                                kaydetmeflag=true;
                            } catch (IOException e) {
                                Log.e("IOException", e.getLocalizedMessage());
                            }
                        } else {
                            Log.i("indirildi", "klasör oluşturulamadı");
                        }



                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }


}
