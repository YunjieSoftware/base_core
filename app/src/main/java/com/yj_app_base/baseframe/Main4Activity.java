package com.yj_app_base.baseframe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yj_app_base.core.base.BaseActivity;

public class Main4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitleWithBack("测试");
//        setTitle("ssss");
    }
}
