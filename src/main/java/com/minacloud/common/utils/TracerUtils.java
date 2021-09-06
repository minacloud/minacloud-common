package com.minacloud.common.utils;

import com.minacloud.common.context.MinaCloudContext;

public class TracerUtils {
    private TracerUtils() {
    }

    public static String getTraceId() {
        return MinaCloudContext.get().getTracerId();
    }
}
