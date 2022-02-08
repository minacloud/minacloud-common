package com.minacloud.common.tracer;

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


