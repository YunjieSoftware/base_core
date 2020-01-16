package com.yj_app_base.core.base.mvp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.CallSuper;

public abstract class BasePresenterImpl<M extends BaseModel,V extends BaseView> implements BasePresenter{
    protected V mView;
    protected M mModel;


    protected abstract M createModel();

    @Override
    public void attachView(BaseView view) {
        this.mView= (V) view;
        if (mModel == null) {
            mModel = createModel();
        }
    }

    @CallSuper
    public void detachView() {
        mModel = null;
        mView = null;
    }

    public Context getContext() {
        return mView.getContext();
    }

    public M getModel() {
        return mModel;
    }

    public void Log(String s){
        Log.e("-----",s);
    }
}
