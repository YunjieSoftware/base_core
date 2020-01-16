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
package com.yj_app_base.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.yj_app_base.core.utils.okgo.JsonConvertor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
  * ━━━━━━神兽出没━━━━━━
  * 　　 ┏┓　  ┏┓
  * 　　┏┛┻━━━━┛┻┓
  * 　　┃　　　　　　　┃
  * 　　┃　　　━　　　┃
  * 　　┃　┳┛　┗┳　┃
  * 　　┃　　　　　　　┃
  * 　　┃　　┻　　　┃
  * 　　┃　　　　　　　┃
  * 　　┗━┓　　　┏━┛
  * 　　　　┃　　　┃
  * 　　　　┃　　　┃  　　
  * 　　　　┃　　　┗━━━┓
  * 　　　　┃　　　　　　　┣┓
  * 　　　　┃　　　　　　　┏┛
  * 　　　　┗┓┓┏━┳┓┏┛
  * 　　　　　┃┫┫　┃┫┫
  * 　　　　　┗┻┛　┗┻┛
  * ━━━━━━代码无bug━━━━━━

  * Copyright (C), 2015-2020-01-09, 石家庄云捷软件科技有限公司
  * @ProjectName:     GsonUtil.java
  *
  * @Package:         com.yj_app_base.core.utils
  *
  * @ClassName:       GsonUtil
  *
  * @Description:     TODO
  *
  * @Author:          Hu_Sir
  *
  * @CreateDate:     2020-01-09 - 14:51
  *
  * @UpdateUser:     ?
  *
  * @UpdateDate:     2020-01-09 - 14:51
  *
  * @UpdateRemark:   todo
  *
 */
public class GsonUtil {

    private static Gson create() {
        return GsonUtil.GsonHolder.gson;
    }

    private static class GsonHolder {
        private static Gson gson = new Gson();
    }

    public static <T> T fromJson(String json, Class<T> type) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(String json, Type type) {
        return create().fromJson(json, type);
    }

    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return create().fromJson(json, classOfT);
    }

    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return create().fromJson(json, typeOfT);
    }

    public static String toJson(Object src) {
        return create().toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return create().toJson(src, typeOfSrc);
    }

    public static String formatJson(String json) {
        try {
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(json);
            return JsonConvertor.getInstance().toJson(je);
        } catch (Exception e) {
            return json;
        }
    }

    public static String formatJson(Object src) {
        try {
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(toJson(src));
            return JsonConvertor.getInstance().toJson(je);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public static JSONObject mapToJsonObject(String... s) {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
//            i % 2 == 0 ? "偶数" : "奇数";
            if ((i + 1) % 2 != 0) {
                map.put(s[i], s[i + 1]);
            }
        }
        JSONObject json = new JSONObject(map);
        return json;
    }
    public static String mapToJsonObjectString(String... s) {
        return mapToJsonObject(s).toString();
    }


    public static JSONArray mapToJsonArray(String... s) {
        JSONObject json = mapToJsonObject(s);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json);
        return jsonArray;
    }
    public static String mapToJsonArrayString(String... s) {
        return mapToJsonArray(s).toString();
    }
}
