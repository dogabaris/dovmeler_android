package com.bigapps.doga.dovmeler;

/**
 * Created by shadyfade on 23.02.2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class StaggeredGridActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staggered_view);
        GridView gv = (GridView) findViewById(R.id.grid_view);
        gv.setAdapter(new StaggeredAdapter(this));
        gv.setOnScrollListener(new ScrollListener(this));
    }
}
