package com.bigapps.doga.dovmeler;

/**
 * Created by shadyfade on 23.02.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        for(String kategoriurl: Data.URLS){
            KategoriViewModel row = new KategoriViewModel(Data.Name[i], kategoriurl);
            kategoriler.add(row);
            i++;
        }

        Adapter adapter = new Adapter(this.getApplicationContext(), kategoriler);
        gv.setAdapter(adapter);
        gv.setOnScrollListener(new ScrollListener(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(StaggeredGridActivity.this,KategoriDetayActivity.class);
                i.putExtra("id", "" + position);
                startActivity(i);
            }
        });

    }
}
