package com.minacloud.common.base;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface BaseEnum {
    String getCode();

    String getDescription();

    static <T extends BaseEnum> T getByCode(Class<T> clazz, String code) {
        T[] enumConstants = clazz.getEnumConstants();
        if (Objects.isNull(enumConstants)) {
            return null;
        }
        Optional<T> first = Arrays.stream(enumConstants).filter(v -> Objects.equals(v.getCode(), code)).findFirst();
        return first.orElse(null);
    }
}
