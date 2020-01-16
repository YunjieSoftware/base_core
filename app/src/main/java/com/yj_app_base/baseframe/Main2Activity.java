//package com.yj_app_base.baseframe;
//
//import android.view.MenuItem;
//import android.view.View;
//
//import androidx.appcompat.widget.Toolbar;
//
//import com.yj_app_base.baseframework.base.activity.BaseActivity;
//
//
//public class Main2Activity extends BaseActivity {
//
//
//    @Override
//    protected void initToolbar() {
//        initBar()
//                .setTitle("主页")
//                .setBackImage(R.mipmap.image_back)
//                .setMenu(R.menu.option_menu, new Toolbar.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        return false;
//                    }
//                })
//                .barShow();
//    }
//
//    @Override
//    protected void initViews() {
//    }
//
//    @Override
//    protected int setLayoutId() {
//        return R.layout.activity_main2;
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//}
