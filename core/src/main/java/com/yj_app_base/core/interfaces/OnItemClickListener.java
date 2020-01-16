package com.yj_app_base.core.interfaces;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemClickListener implements BaseQuickAdapter.OnItemClickListener {

    //两次点击按钮之间的间隔，目前为1000ms
    private  final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime;

    public abstract void onSingleItemClick(BaseQuickAdapter adapter, View view, int position);

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onSingleItemClick(adapter,view,position);
        }else {
            //点击的太快了
        }
    }
}
