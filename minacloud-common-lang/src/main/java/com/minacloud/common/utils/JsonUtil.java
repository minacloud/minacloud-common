package com.minacloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Reader;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    public static JsonObject toJsonObject(Object object) {
        JsonElement jsonElement = gson.toJsonTree(object);
        return jsonElement.getAsJsonObject();
    }

    public static JsonElement toJsonElement(Object object) {
        return gson.toJsonTree(object);
    }

    public static void addProperty(JsonObject object, String key, Object value) {
        object.add(key, toJsonElement(value));
    }

    public static JsonObject toJsonObject(String key1, Object value1) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        jsonObject.add(key4, toJsonElement(value4));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4, String key5, Object value5) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        jsonObject.add(key4, toJsonElement(value4));
        jsonObject.add(key5, toJsonElement(value5));
        return jsonObject;
    }

    public static JsonObject toJsonObject(Map<String, Object> objectMap) {
        JsonObject jsonObject = new JsonObject();
        objectMap.forEach((k, v) -> jsonObject.add(k, toJsonElement(v)));
        return jsonObject;
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
