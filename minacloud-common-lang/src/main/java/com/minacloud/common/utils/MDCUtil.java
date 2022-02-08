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
