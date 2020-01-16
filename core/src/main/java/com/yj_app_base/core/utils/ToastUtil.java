package com.yj_app_base.core.utils;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.toast.XToast;
import com.yj_app_base.core.base.YJBASEUI;

public class ToastUtil {
    public static void toast(String s) {
        XToast.normal(YJBASEUI.getContext(), s).show();
    }

    public static void errorToast(String s) {
        XToast.error(XUI.getContext(), s).show();
    }

    public static void successToast(String s) {
        XToast.success(XUI.getContext(), s).show();
    }

    public static void infoToast(String s) {
        XToast.info(XUI.getContext(), s).show();
    }

    public static void warningToast(String s) {
        XToast.warning(XUI.getContext(), s).show();
    }


}
