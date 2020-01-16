package com.yj_app_base.core.utils.okgo;
/*
 * @Author Hu
 * @Emil 87683202@qq.com
 * Create at 2019/3/28 - 8:24
 * Description:
 */


import com.yj_app_base.core.utils.ToastUtil;

import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.security.auth.login.LoginException;

public class MyException {


    public static void doException(Exception e) {
        e(e);
    }

    public static void doException(Throwable exception) {

        e(exception);
    }

    private static void e(Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            ToastUtil.errorToast("网络连接失败，请连接网络....");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.errorToast("网络请求超时");
        } else if (e instanceof HttpRetryException) {
            ToastUtil.errorToast("服务器拒绝请求");
        } else if (e instanceof SocketException) {
            ToastUtil.errorToast("网络请求取消！");
        } else if (e instanceof LoginException) {
            ToastUtil.toast(e.getMessage());
        } else {
            ToastUtil.errorToast("网络错误！" + e);
        }
    }


}
