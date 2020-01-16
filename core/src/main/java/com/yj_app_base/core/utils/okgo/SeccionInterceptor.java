package com.yj_app_base.core.utils.okgo;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class SeccionInterceptor implements Interceptor {
    String action;
    SecCallBack callback;
    public SeccionInterceptor(String action, SecCallBack callback) {
        this.action=action;
        this.callback=callback;

    }
    public SeccionInterceptor(SecCallBack callback) {
        this.callback=callback;
    }
    public SeccionInterceptor() {
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        RequestBody oldRequestBody = request.body();
        if (null != oldRequestBody) {
            Buffer requestBuffer = new Buffer();
            oldRequestBody.writeTo(requestBuffer);
            String oldBodyStr = requestBuffer.readUtf8();
            String url = request.url().toString();
            Log.e("okgo请求链接=",url);
            Log.e("okgo发送参数val=", oldBodyStr);
            requestBuffer.close();
        }

        // application/json;charset=UTF-8  image/jpeg

        //响应
        Response response = chain.proceed(request);
        if (response.code() == 200) {
            //只有约定的返回码才经过加密，才需要走解密的逻辑
            ResponseBody oldResponseBody = response.body();
            MediaType mediaType = oldResponseBody.contentType();
            String s = mediaType.toString();
            if (s.contains("image") || s.contains("jpeg") || s.contains("IMAGE") || s.contains("JPEG")) {
            } else {
                String oldResponseBodyStr = oldResponseBody.string();
                Log.e("okgo返回的数据=", oldResponseBodyStr);
                oldResponseBody.close();
                if (oldResponseBodyStr.contains("javascript") || oldResponseBodyStr.contains(".html") || oldResponseBodyStr.contains(".HTML")||oldResponseBodyStr.contains("code:5008")) {
                    if (TextUtils.isEmpty(action)){
                        callback.jumpcallback();
                    }else {

                        callback.jumpcallback(action);
                    }
                 } else {
                    //构造新的response
                    ResponseBody newResponseBody = ResponseBody.create(mediaType, oldResponseBodyStr);
                    response = response.newBuilder().body(newResponseBody).build();
                }
                response.close();
            }

        }
        //返回
        return response;
    }

  public   interface SecCallBack{
        void jumpcallback(String action);
        void jumpcallback();
    }

}
