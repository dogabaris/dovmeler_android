package com.bigapps.doga.dovmeler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by shadyfade on 01.03.2016.
 */
public class DovmeDetay extends Activity{
    private static String link;
    private String DovmeYolu;
    private boolean kaydetmeflag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dovmedetay_layout);

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

        Button indir_btn = (Button) findViewById(R.id.indir_btn);


        indir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDownload(getApplicationContext(), link);
            }
        });
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
