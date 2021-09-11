package com.minacloud.common.utils;

import com.google.gson.Gson;

import java.io.Reader;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {

        return gson.toJson(object);
    }

    public static <T> T parseObject(String json, Class<T> clzz) {
        return gson.fromJson(json, clzz);
    }

    public static <T> T parseObject(Reader json, Class<T> clzz) {
        return gson.fromJson(json, clzz);
    }

    public static <T> List<T> parseArray(String json, Class<List<T>> clzz) {
        return gson.fromJson(json, clzz);
    }

    public static <T> List<T> parseArray(Reader json, Class<List<T>> clzz) {
        return gson.fromJson(json, clzz);
    }
}
