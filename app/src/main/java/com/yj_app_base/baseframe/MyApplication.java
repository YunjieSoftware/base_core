package com.yj_app_base.baseframe;

import android.app.Application;


import com.xuexiang.xui.XUI;
import com.xuexiang.xutil.data.DateUtils;
import com.yj_app_base.core.base.YJBASEUI;

import java.util.Date;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YJBASEUI.init(this);
        XUI.init(this);
        Date date = DateUtils.getNowDate();
    }
}
