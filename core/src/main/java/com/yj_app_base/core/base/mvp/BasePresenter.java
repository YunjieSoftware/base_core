package com.yj_app_base.core.base.mvp;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public interface BasePresenter <V extends BaseView & LifecycleOwner> extends LifecycleObserver {
        /**
     * 创建view时调用  调用在initView 之后
     */
    void attachView(V view);

    /**
     * view销毁时调用释放资源
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detachView();

    Context getContext();
}
