package com.minacloud.common.processor;

import cn.hutool.core.text.CharSequenceUtil;
import com.minacloud.common.base.BaseProcessor;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.manage.ProcessorManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Configuration
@Slf4j
public class RequestProcessorListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ProcessorManager.clear();
        Class<? extends Annotation> annotationClass = Processor.class;
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationClass);
        Set<Map.Entry<String, Object>> entitySet = beansWithAnnotation.entrySet();
        for (Map.Entry<String, Object> entry : entitySet) {
            Object value = entry.getValue();
            Class<?> clazz = value.getClass();
            Processor componentDesc = AnnotationUtils.findAnnotation(clazz, Processor.class);
            String action = Objects.requireNonNull(componentDesc).value();
            if (ProcessorManager.hasAction(action)) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "[" + action + "] Action Config Duplicated");
            }
            log.info("register action ===> {}", action);
            action = CharSequenceUtil.removePrefix(action, "/");
            ProcessorManager.putAction(action, (BaseProcessor) value);
        }
    }
}
