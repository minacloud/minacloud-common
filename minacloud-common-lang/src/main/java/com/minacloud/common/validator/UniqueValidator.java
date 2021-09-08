/*
 * Copyright © 2021 Laysan (lslvxy@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minacloud.common.validator;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.minacloud.common.annotation.Unique;
import com.minacloud.common.annotation.UniqueColumn;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>UniqueValidator class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {


    private UniqueColumn[] columns;
    private String message;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Unique unique) {
        columns = unique.columns();
        message = unique.message();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Map<String, Object> invalidMap = countRows(value);
        if (ObjectUtil.isNotNull(invalidMap)) {
            Map.Entry<String, Object> field = invalidMap.entrySet().iterator().next();
            context.unwrap(HibernateConstraintValidatorContext.class)
                    .addExpressionVariable("name", value.getClass().getSimpleName())
                    .addExpressionVariable("fullName", value.getClass().getName())
                    .addExpressionVariable("field", field.getKey())
                    .addExpressionVariable("value", field.getValue())
                    .addExpressionVariable("allFields", StrUtil.join(", ", invalidMap.keySet()))
                    .addExpressionVariable("values", StrUtil.join(", ", invalidMap.values()))
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field.getKey())
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }

    private Map<String, Object> countRows(Object value) {
        List<Map<String, Object>> fieldValueCombos = new ArrayList<>();

        if (columns.length > 0) {
            fieldValueCombos = prepareColumns(value);
        }

        for (Map<String, Object> fieldMap : fieldValueCombos) {
            if (hasRecord(value, fieldMap)) {
                return fieldMap;
            }
        }

        return null;
    }

    private boolean hasRecord(Object value, Map<String, Object> fieldMap) {
        return true;
    }

    private List<Map<String, Object>> prepareColumns(Object value) {
        return Arrays.stream(columns)
                .map(column -> {
                    if (column.fields().length == 1) {
                        Map<String, Object> result = new HashMap<>();
                        String fieldName = column.fields()[0];
                        Object val = ReflectUtil.getFieldValue(value, fieldName);
                        result.put(fieldName, val);
                        return result;
                    }

                    return fieldSetToMap(column.fields(), value);
                })
                .filter(item -> item.size() > 0)
                .collect(toListOrEmpty());
    }

    private Map<String, Object> fieldSetToMap(String[] fieldSet, Object value) {
        return Arrays.stream(fieldSet).collect(Collectors.toMap(
                Function.identity(),
                fieldName -> ReflectUtil.getFieldValue(value, fieldName)
        ));
    }

    private static <T> Collector<T, ?, List<T>> toListOrEmpty() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                l -> l.isEmpty() ? new ArrayList<>() : l
        );
    }

}
