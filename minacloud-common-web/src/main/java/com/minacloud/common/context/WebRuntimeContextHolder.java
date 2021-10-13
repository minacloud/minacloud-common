package com.minacloud.common.context;

/*-
 * #%L
 * minacloud-common-web
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

public class WebRuntimeContextHolder {
    private static final ThreadLocal<WebRuntimeContext> WEB_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static WebRuntimeContext createWebRuntimeContext() {
        WebRuntimeContext context = new WebRuntimeContext();
        WEB_CONTEXT_THREAD_LOCAL.set(context);
        return context;
    }

    public static WebRuntimeContext getWebRuntimeContext() {
        return WEB_CONTEXT_THREAD_LOCAL.get();
    }

    public static void cleanWebRuntimeContext() {
        WEB_CONTEXT_THREAD_LOCAL.remove();
    }
}
