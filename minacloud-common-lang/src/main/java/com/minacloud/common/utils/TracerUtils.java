package com.minacloud.common.utils;

import com.minacloud.common.context.MinaCloudContext;

public class TracerUtils {
    public static String HEADER_TRACEID = "traceId";

    private TracerUtils() {
    }

    public static String getTraceId() {
        return MinaCloudContext.get().getTracerId();
    }

    public static void setTraceId(String traceId) {
        MinaCloudContext.get().setTracerId(traceId);
    }
}
