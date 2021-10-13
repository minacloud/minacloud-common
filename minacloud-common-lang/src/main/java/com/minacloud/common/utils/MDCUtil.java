package com.minacloud.common.utils;

import com.minacloud.common.tracer.TracerUtil;
import io.opentracing.Span;
import org.slf4j.MDC;

public class MDCUtil {
    public static final String MDC_TRACER_ID = "TracerId";
    public static final String MDC_TENANT_ID = "tenantId";

    public static void logStartedSpan(Span span) {
        if (span != null) {
            MDC.put(MDC_TRACER_ID, TracerUtil.getTracerId());
        }
    }

    public static void logStoppedSpan() {
        MDC.remove(MDC_TRACER_ID);
        Span span = TracerUtil.getSpan();
        if (span != null) {
            MDC.put(MDC_TRACER_ID, TracerUtil.getTracerId());
        }
    }

    public static void logTenantId(String tenantId) {
        MDC.put(MDC_TENANT_ID, tenantId);
    }

    public static void clearTenantId() {
        MDC.remove(MDC_TENANT_ID);
    }

}
