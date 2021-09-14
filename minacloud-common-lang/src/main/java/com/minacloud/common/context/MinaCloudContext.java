package com.minacloud.common.context;

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
