package com.bigapps.doga.dovmeler;

import android.app.Activity;
import android.os.Bundle;
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
        Kategori_Ismi = KategoriData.KategoriDetay[Integer.parseInt(id)];

        for (int i=1;i<=200;i++){
            if(i<10){
                kategori_icerik.add(KategoriData.BASE + Kategori_Ismi + "00" + i + "_595" + KategoriData.EXT);
            }else if(i<100){
                kategori_icerik.add(KategoriData.BASE + Kategori_Ismi + "0" + i + "_595" + KategoriData.EXT);
            }else if(i>100){
                kategori_icerik.add(KategoriData.BASE + Kategori_Ismi + i + "_595" + KategoriData.EXT);
            }
        }

        GridView gv = (GridView) findViewById(R.id.kategori_icerik_gv);
        gv.setAdapter(new StaggeredAdapter(this,kategori_icerik));
        gv.setOnScrollListener(new ScrollListener(this));

    }
}
