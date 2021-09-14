package com.minacloud.common.manage;

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

import com.minacloud.common.base.BaseProcessor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class ProcessorManager {
    private static Map<String, BaseProcessor> processorMap = new ConcurrentHashMap<>();

    public static void putAction(String action, BaseProcessor processor) {
        processorMap.put(action, processor);
    }

    public static BaseProcessor getProcessorByAction(String action) {
        return processorMap.getOrDefault(action, null);
    }

    public static boolean hasAction(String action) {
        return processorMap.containsKey(action);
    }

    public static void clear() {
        processorMap.clear();
    }
}
