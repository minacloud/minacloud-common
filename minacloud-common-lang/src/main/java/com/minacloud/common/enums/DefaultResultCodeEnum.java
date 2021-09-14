package com.minacloud.common.enums;

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

import com.minacloud.common.result.ResultCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum DefaultResultCodeEnum implements ResultCode {
    /**
     * Success
     */
    SUCCESS(ResultStatusEnum.SUCCESS, "Success"),
    /**
     * Fail
     */
    FAIL(ResultStatusEnum.FAIL, "Fail"),
    /**
     * Parameter Illegal
     */
    PARAM_ILLEGAL(ResultStatusEnum.FAIL, "Parameter Illegal"),
    /**
     * Access Denied
     */
    ACCESS_DENIED(ResultStatusEnum.FAIL, "Access Denied"),
    /**
     * Resource Not Found
     */
    RES_NOT_FOUND(ResultStatusEnum.FAIL, "Resource Not Found"),
    /**
     * Method Not Supported
     */
    METHOD_NOT_SUPPORTED(ResultStatusEnum.FAIL, "Method Not Supported"),
    /**
     * Server Error
     */
    SERVER_ERROR(ResultStatusEnum.FAIL, "Server Error"),
    /**
     * Unknown
     */
    UNKNOWN(ResultStatusEnum.UNKNOWN, "Unknown");

    private final String resultStatus;
    private final String resultMessage;

    DefaultResultCodeEnum(ResultStatusEnum resultStatus, String resultMessage) {
        this.resultStatus = resultStatus.getCode();
        this.resultMessage = resultMessage;
    }

    @Override
    public String getResultCode() {
        return name();
    }

    public static DefaultResultCodeEnum getByResultCode(String resultCode) {
        return Arrays.stream(DefaultResultCodeEnum.values()).filter(v -> Objects.equals(v.getResultCode(), resultCode)).findFirst().orElse(UNKNOWN);
    }
}
