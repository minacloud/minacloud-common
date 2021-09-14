package com.minacloud.common.base;

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
