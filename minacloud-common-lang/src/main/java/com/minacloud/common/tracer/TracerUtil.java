package com.minacloud.common.tracer;

import com.minacloud.common.utils.MDCUtil;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.TextMap;

public class TracerUtil {
    private static TracerSpi tracerSpi;

    public static Span getSpan() {
        return tracerSpi.getCurrentSpan();
    }

    public static Span createSpan(String moduleType) {
        return tracerSpi.createSpan(moduleType);
    }

    public static TextMap getSpanMap() {
        return tracerSpi.getSpanMap();
    }

    public static String getTracerId() {
        return tracerSpi.getTracerId();
    }

    public static Tracer getTracer() {
        return tracerSpi.getTracer();
    }

    public static Tracer createTracer(String type) {
        return tracerSpi.createTracer(type);
    }


    public static void putSpan(Span span) {
        tracerSpi.putSpan(span);
    }

    public static void logTraceId(Span span) {
        MDCUtil.logStartedSpan(span);
    }

    public static void clearTraceId() {
        MDCUtil.logStoppedSpan();
    }

    public static void setTracerSpi(TracerSpi tracerSpi) {
        TracerUtil.tracerSpi = tracerSpi;
    }

    public static TracerSpi getTracerSpi() {
        return tracerSpi;
    }
}


