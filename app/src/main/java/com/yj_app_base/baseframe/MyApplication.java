package com.yj_app_base.baseframe;

import android.app.Application;

import com.yj_app_base.core.base.YJBASEUI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YJBASEUI.init(this);
    }
}
