package com.minacloud.common.manage;

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
