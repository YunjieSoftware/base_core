package com.yj_app_base.core.interfaces;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

    //两次点击按钮之间的间隔，目前为1000ms
    private  final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime;

    public abstract void onSingleItemChildClick(BaseQuickAdapter adapter, View view, int position);

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onSingleItemChildClick(adapter,view,position);
        }else {
            //点击的太快了
        }
    }
}
