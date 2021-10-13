package com.minacloud.common.tracer;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.propagation.TextMap;

public interface TracerSpi {
    Span getCurrentSpan();

    TextMap getSpanMap();

    String getTracerId();

    Tracer createTracer(String tracerType);

    Span createSpan(String moduleType);

    Tracer getTracer();

    void clear();

    void putSpan(Span span);

    void setContextCarrier(SpanBuilder spanBuilder, Object contextCarrier);

    Object createContextCarrier();
}
