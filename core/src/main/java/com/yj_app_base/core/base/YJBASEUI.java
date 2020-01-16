/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.yj_app_base.core.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.yj_app.http_module.OkGo;
import com.yj_app.http_module.cache.CacheEntity;
import com.yj_app.http_module.cache.CacheMode;
import com.yj_app.http_module.interceptor.HttpLoggingInterceptor;
import com.yj_app.http_module.model.HttpHeaders;
import com.yj_app_base.core.utils.okgo.SeccionInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;


/**
 * UI全局设置
 *
 * @author xuexiang
 * @since 2018/11/14 上午11:40
 */
public class YJBASEUI {

    private static volatile YJBASEUI sInstance = null;

    private static Application sContext;

    private static boolean sIsTabletChecked;

    private static int sScreenType;

    private static int resid = 0;

    private String BaseUrl;
    private HttpHeaders headers;

    private YJBASEUI() {

    }

    /**
     * 获取单例
     *
     * @return
     */
    public static YJBASEUI getInstance() {
        if (sInstance == null) {
            synchronized (YJBASEUI.class) {
                if (sInstance == null) {
                    sInstance = new YJBASEUI();
                }
            }
        }
        return sInstance;
    }

    //=======================初始化设置===========================//

    /**
     * 初始化
     *
     * @param context
     */
    public static YJBASEUI init(Application context) {
        sContext = context;
        return getInstance();
    }

    public YJBASEUI initOkGo(SeccionInterceptor.SecCallBack secCallBack) {


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
//        builder.addInterceptor(new MyInterceptor());
        if (null == secCallBack) {
            builder.addInterceptor(new SeccionInterceptor());
        } else {
            builder.addInterceptor(new SeccionInterceptor(secCallBack));
        }
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
//        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
//        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init((Application) getContext())                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                           //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);                       //全局公共参数
        return this;
    }


    public static void setNavigationIcon(int rid) {
        resid = rid;
    }

    public static int getNavigationIcon() {
        return resid;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 YJBASEUI.init() 初始化！");
        }
    }

    public String getBaseUrl() {
        testInitialize();
        return (TextUtils.isEmpty(BaseUrl) || TextUtils.equals("null", BaseUrl)) ? "" : BaseUrl;
    }

    public YJBASEUI setBaseUrl(String baseUrl) {
        BaseUrl = (TextUtils.isEmpty(baseUrl) ? "" : baseUrl);
        return this;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpHeaders headers() {
        if (headers == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 YJBASEUI.headers()的信息！");
        }
        return headers;
    }

    public YJBASEUI setHeaders(String key, String value) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.put(key, value);
        return this;
    }
}
