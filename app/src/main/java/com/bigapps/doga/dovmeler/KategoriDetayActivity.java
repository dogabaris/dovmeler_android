package com.bigapps.doga.dovmeler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadyfade on 26.02.2016.
 */
public class KategoriDetayActivity extends Activity {
    List<String> kategori_icerik = new ArrayList<String>();
    String Kategori_Ismi;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategoridetay_layout);

        id = getIntent().getExtras().getString("id");
        Kategori_Ismi = Data.KategoriDetay[Integer.parseInt(id)];

        for (int i=1;i<=200;i++){
            if(i<10){
                kategori_icerik.add(Data.BASE + Kategori_Ismi + "00" + i + "_595" + Data.EXT);
            }else if(i<100){
                kategori_icerik.add(Data.BASE + Kategori_Ismi + "0" + i + "_595" + Data.EXT);
            }else if(i>100){
                kategori_icerik.add(Data.BASE + Kategori_Ismi + i + "_595" + Data.EXT);
            }
        }

        GridView gv = (GridView) findViewById(R.id.kategori_icerik_gv);
        gv.setAdapter(new StaggeredAdapter(this,kategori_icerik));
        gv.setOnScrollListener(new ScrollListener(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(KategoriDetayActivity.this, DovmeDetay.class);
                String link = kategori_icerik.get(position);

                i.putExtra("link", "" + link);
                i.putExtra("position", "" + position);
                startActivity(i);
            }
        });

    }
}
