package com.bigapps.doga.dovmeler;

/**
 * Created by shadyfade on 23.02.2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends Activity {
    List<KategoriViewModel> kategoriler = new ArrayList<KategoriViewModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staggered_view);

        GridView gv = (GridView) findViewById(R.id.grid_view);
        //gv.setAdapter(new StaggeredAdapter(this));
        //gv.setOnScrollListener(new ScrollListener(this));

        int i=0;
        for(String kategoriurl:KategoriData.URLS){
            KategoriViewModel row = new KategoriViewModel(KategoriData.Name[i], kategoriurl);
            kategoriler.add(row);
            i++;
        }

        Adapter adapter = new Adapter(this.getApplicationContext(), kategoriler);
        gv.setAdapter(adapter);
        gv.setOnScrollListener(new ScrollListener(this));
    }
}
