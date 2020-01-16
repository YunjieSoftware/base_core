/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yj_app_base.core.utils.okgo;

import android.app.Activity;
import android.util.Log;

import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.yj_app.http_module.model.Response;
import com.yj_app.http_module.request.base.Request;


/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    MiniLoadingDialog dialog;
    private void initDialog(Activity activity) {


        if (dialog==null){
            dialog =  WidgetUtils.getMiniLoadingDialog(activity,"加载中……");
            dialog.setCanceledOnTouchOutside(false);
        }

    }

    public DialogCallback(Activity activity) {
        super();
        if (dialog==null){

            initDialog(activity);
        }
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Response<T> response) {
        Log.e("error=",response.getException().toString());
        MyException.doException(response.getException());
    }
}