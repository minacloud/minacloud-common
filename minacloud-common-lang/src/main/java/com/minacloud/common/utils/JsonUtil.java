package com.minacloud.common.utils;

/*-
 * #%L
 * minacloud-common-lang
 * %%
 * Copyright (C) 2021 minacloud
 * %%
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
 * #L%
 */

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
