package com.bigapps.doga.dovmeler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by shadyfade on 26.02.2016.
 */
public class Row {
    // This is a reference to the layout we defined
    public static final int LAYOUT = R.layout.kategori_layout;

    private final Context context;
    private final TextView textView;
    private final SquaredImageView imageView;

    protected Row(Context context, View convertView) {
        this.context = context;
        this.imageView = (SquaredImageView) convertView.findViewById(R.id.kategori_iv);
        this.textView = (TextView) convertView.findViewById(R.id.kategori_tv);
    }

    public void bind(KategoriViewModel exampleViewModel) {
        this.textView.setText(exampleViewModel.getText());
        Picasso.with(this.context)
                .load(exampleViewModel.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .tag(context)
                .into(this.imageView);
    }


}
