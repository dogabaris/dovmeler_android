package com.bigapps.doga.dovmeler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shadyfade on 23.02.2016.
 */
public class StaggeredAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> urls = new ArrayList<String>();

    public StaggeredAdapter(Context context) {
        this.context = context;

        // Ensure we get a different ordering of images on each run.
        Collections.addAll(urls, Data.URLS);
        //Collections.shuffle(urls);

        // Triple up the list.
        ArrayList<String> copy = new ArrayList<String>(urls);
        //urls.addAll(copy);
        //urls.addAll(copy);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .fit() //
                .tag(context) //
                .into(view);

        return view;
    }

    @Override public int getCount() {
        return urls.size();
    }

    @Override public String getItem(int position) {
        return urls.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }
}

    /*mBackgroundColors.add(R.drawable.ari_dovme_modelleri_007_595);
        mBackgroundColors.add(R.drawable.ask_dovme_modelleri_025_595);
        mBackgroundColors.add(R.drawable.aslan_dovme_modelleri_031_595);
        mBackgroundColors.add(R.drawable.ates_dovme_modelleri_063_595);
        mBackgroundColors.add(R.drawable.ay_dovme_modelleri_008_595);
        mBackgroundColors.add(R.drawable.balik_dovme_modelleri_001_595);
        mBackgroundColors.add(R.drawable.bel_dovme_modelleri_062_595);
        mBackgroundColors.add(R.drawable.burc_dovme_modelleri_011_595);
        mBackgroundColors.add(R.drawable.celtic_dovme_modelleri_010_595);
        mBackgroundColors.add(R.drawable.cicek_dovme_modelleri_067_595);
        mBackgroundColors.add(R.drawable.dovmeakrep_dovme_modelleri_016_595);
        mBackgroundColors.add(R.drawable.dovmedort_yaprakli_yonca_dovme_modelleri_013_595);
        mBackgroundColors.add(R.drawable.dragon_dovme_modelleri_057_595);
        mBackgroundColors.add(R.drawable.feminen_dovme_modelleri_036_595);
        mBackgroundColors.add(R.drawable.goz_dovme_modelleri_005_595);
        mBackgroundColors.add(R.drawable.gul_dovme_modelleri_002_595);
        mBackgroundColors.add(R.drawable.gunes_dovme_modelleri_015_595);
        mBackgroundColors.add(R.drawable.hac_dovme_modelleri_001_595);
        mBackgroundColors.add(R.drawable.isa_dovme_modelleri_011_595);
        mBackgroundColors.add(R.drawable.japon_dovme_modelleri_012_595);
        mBackgroundColors.add(R.drawable.japon_seytan_dovme_modelleri_002_595);
        mBackgroundColors.add(R.drawable.joker_dovme_modelleri_060_595);
        mBackgroundColors.add(R.drawable.kalp_dovme_modelleri_098_595);
        mBackgroundColors.add(R.drawable.kanat_dovme_modelleri_008_595);
        mBackgroundColors.add(R.drawable.kanji_dovme_modelleri_017_595);
        mBackgroundColors.add(R.drawable.kaplan_dovme_modelleri_012_595);
        mBackgroundColors.add(R.drawable.kartal_dovme_modelleri_051_595);
        mBackgroundColors.add(R.drawable.kedikedi_dovme_modelleri_018_595);
        mBackgroundColors.add(R.drawable.kelebek_dovme_modelleri_015_595);
        mBackgroundColors.add(R.drawable.kizilderili_dovme_modelleri_051_595);
        mBackgroundColors.add(R.drawable.koi_fish_dovme_modelleri_005_595);
        mBackgroundColors.add(R.drawable.kol_banti_dovme_modelleri_036_595);
        mBackgroundColors.add(R.drawable.kurbaga_dovme_modelleri_012_595);
        mBackgroundColors.add(R.drawable.kurt_dovme_modelleri_041_595);
        mBackgroundColors.add(R.drawable.kuru_kafa_dovme_modelleri_028_595);
        mBackgroundColors.add(R.drawable.lotus_nilufer_dovme_modelleri_031_595);
        mBackgroundColors.add(R.drawable.mantar_dovme_modelleri_016_595);
        mBackgroundColors.add(R.drawable.melek_dovme_modelleri_052_595);
        mBackgroundColors.add(R.drawable.misir_dovme_modelleri_006_595);
        mBackgroundColors.add(R.drawable.muzik_dovme_modelleri_043_595);
        mBackgroundColors.add(R.drawable.orumcek_dovme_modelleri_052_595);
        mBackgroundColors.add(R.drawable.pence_pati_dovme_modelleri_015_595);
        mBackgroundColors.add(R.drawable.peri_dovme_modelleri_032_595);
        mBackgroundColors.add(R.drawable.savasci_dovme_modelleri_008_595);
        mBackgroundColors.add(R.drawable.seytan_dovme_modelleri_011_595);
        mBackgroundColors.add(R.drawable.silah_dovme_modelleri_032_595);
        mBackgroundColors.add(R.drawable.supermen_dovme_modelleri_028_595);
        mBackgroundColors.add(R.drawable.tribal_dovme_modelleri_070_595);
        mBackgroundColors.add(R.drawable.wiking_dovme_modelleri_018_595);
        mBackgroundColors.add(R.drawable.yakuza_dovme_modelleri_037_595);
        mBackgroundColors.add(R.drawable.yilan_dovme_modelleri_011_595);
        mBackgroundColors.add(R.drawable.yildiz_dovme_modelleri_025_595);
        mBackgroundColors.add(R.drawable.yunus_dovme_modelleri_008_595);
        mBackgroundColors.add(R.drawable.yusufcuk_dovme_modelleri_025_595);
        mBackgroundColors.add(R.drawable.zar_dovme_modelleri_049_595);*/

