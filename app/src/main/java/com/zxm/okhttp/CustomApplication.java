package com.zxm.okhttp;

import android.app.Application;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.getInstance().initialize(this);
    }
}
