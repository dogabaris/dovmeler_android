package com.bigapps.doga.dovmeler;

import android.app.Activity;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

/**
 * Created by shadyfade on 01.03.2016.
 */
public class DovmeDetay extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dovmedetay_layout);

        String id = getIntent().getExtras().getString("id");
        String link = getIntent().getExtras().getString("link");

        SquaredImageView view = (SquaredImageView) findViewById(R.id.dovme_iv);

        Picasso.with(getApplicationContext()) //
                .load(link) //
                .placeholder(R.drawable.progress_animation) //
                .error(R.drawable.error) //
                .fit() //
                .tag(getApplicationContext()) //
                .into(view);
    }
}
