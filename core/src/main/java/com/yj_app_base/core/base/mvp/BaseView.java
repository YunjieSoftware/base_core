package com.yj_app_base.core.base.mvp;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

public interface BaseView extends LifecycleOwner {
    Context getContext();
}
