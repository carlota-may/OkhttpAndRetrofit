package com.zxm.okhttp.application;

import android.app.Application;

import com.zxm.okhttp.net.ApiClient;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.getInstance().initialize(this);
    }
}
