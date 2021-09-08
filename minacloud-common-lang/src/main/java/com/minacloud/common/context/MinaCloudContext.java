package com.minacloud.common.context;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MinaCloudContext {
    private static ThreadLocal<MinaCloudContext> threadLocal = new ThreadLocal<>();
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    private String tracerId = "";

    public static MinaCloudContext get() {
        MinaCloudContext context = threadLocal.get();
        if (Objects.isNull(context)) {
            context = new MinaCloudContext();
            threadLocal.set(context);
        }
        return context;
    }

    public static void set(MinaCloudContext context) {
        threadLocal.set(context);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void remove(String key) {
        MinaCloudContext context = threadLocal.get();
        if (Objects.nonNull(context)) {
            context.getAttributes().remove(key);
        }
    }

    public MinaCloudContext newInstance() {
        MinaCloudContext context = new MinaCloudContext();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            context.getAttributes().put(entry.getKey(), entry.getValue());
        }
        return context;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getTracerId() {
        return tracerId;
    }

    public void setTracerId(String tracerId) {
        this.tracerId = tracerId;
    }
}
