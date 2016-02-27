package com.bigapps.doga.dovmeler;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class MyApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance()
                .init(config);
    }

    public static Context getContext() {
        return mContext;
    }
    public static void setContext(Context mContext) {
        MyApplication.mContext = mContext;
    }
}

