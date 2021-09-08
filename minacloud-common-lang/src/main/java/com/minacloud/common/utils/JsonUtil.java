package com.minacloud.common.utils;

import com.google.gson.Gson;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }
}
