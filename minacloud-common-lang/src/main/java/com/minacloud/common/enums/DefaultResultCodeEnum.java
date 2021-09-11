package com.minacloud.common.enums;

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
