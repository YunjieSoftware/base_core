//package com.yj_app_base.baseframe;
//
//import android.content.Intent;
//import android.view.View;
//import android.widget.TextView;
//
//import com.yj_app_base.baseframework.base.activity.BaseActivity;
//
//
//public class MainActivity extends BaseActivity {
//    TextView test;
//    @Override
//    public void onClick(View v) {
//
//    }
//
//
//    @Override
//    protected void initToolbar() {
//        initBar().setTitle("主页","小碧蹄").setBackImage(R.mipmap.image_back).barShow();
//    }
//
//    @Override
//    protected void initViews() {
//        test=findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Main2Activity.class));
//            }
//        });
//    }
//
//    @Override
//    public int setLayoutId() {
//        return R.layout.activity_main;
//    }
//
//
//}
