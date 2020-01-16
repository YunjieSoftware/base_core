package com.yj_app_base.core.base.mvp;

import android.os.Bundle;

import com.yj_app_base.core.base.BaseActivity;


public abstract class BaseMvpActivity<P extends BasePresenter,V extends BaseView> extends BaseActivity {
   public P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.attachView((V)this);
    }

    // 实例化presenter
    public abstract P initPresenter();
    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
