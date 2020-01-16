package com.yj_app_base.core.base.mvp;


import com.yj_app.http_module.OkGo;
import com.yj_app.http_module.model.HttpParams;
import com.yj_app_base.core.base.YJBASEUI;
import com.yj_app_base.core.utils.okgo.DialogCallback;

import java.io.File;
import java.util.List;

public class BaseModelImpl  {
    public void post(String datainterface, DialogCallback dialogCallback, String... keyvalue) {
        OkGo.post(YJBASEUI.getInstance().getBaseUrl()+datainterface)
                .params(kv2Map(keyvalue))
                .headers(YJBASEUI.getInstance().headers())
                .execute(dialogCallback);
    }
    public void post(String datainterface, DialogCallback dialogCallback) {
        post(datainterface,dialogCallback,"","");
    }
    public void postfile(String datainterface, DialogCallback dialogCallback, List<File> files, String... keyvalue) {
        OkGo.post(YJBASEUI.getInstance().getBaseUrl()+datainterface)
                .params(kv2Map(keyvalue))
                .isMultipart(true)
                .addFileParams("signFile", files).headers(YJBASEUI.getInstance().headers())
                .execute(dialogCallback);
    }

    public HttpParams kv2Map(String... s) {
        HttpParams params = new HttpParams();
        for (int i = 0; i < s.length; i++) {
//            i % 2 == 0 ? "偶数" : "奇数";
            if ((i + 1) % 2 != 0) {
                params.put(s[i].trim(), s[i + 1].trim());
            }
        }
        return params;
    }
}
