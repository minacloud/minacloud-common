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
package com.minacloud.common.exception;


import com.minacloud.common.enums.DefaultResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MinaCloudParamIllegalException extends MinaCloudBusinessException {
    private List<Detail> details;

    public MinaCloudParamIllegalException(String message) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL,message);
    }

    public MinaCloudParamIllegalException(String property, String message) {
        this(message, Collections.singletonList(new Detail(property, message, null)));
    }

    public MinaCloudParamIllegalException(String message, List<Detail> details) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL,message);
        this.details = details;
    }

    public MinaCloudParamIllegalException(String message, Set<? extends ConstraintViolation<?>> violations) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL,message);
        if (null != violations && !violations.isEmpty()) {
            details = new ArrayList<>();
            for (ConstraintViolation<?> violation : violations) {
                details.add(new Detail(violation.getPropertyPath().toString(), violation.getMessage(), null));
            }
        }
    }



    @Getter
    @Setter
    @AllArgsConstructor
    public static class Detail {
        String property;

        String message;

        Object detail;
    }
}
