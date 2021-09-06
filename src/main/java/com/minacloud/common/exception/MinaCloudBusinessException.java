package com.minacloud.common.exception;

import com.alibaba.cola.exception.BizException;
import com.minacloud.common.result.ResultCode;

public class MinaCloudBusinessException extends BizException {
    private static final long serialVersionUID = 1L;

    private final ResultCode resultCode;

    public MinaCloudBusinessException(ResultCode resultCode) {
        super(resultCode.getResultCode(), resultCode.getResultMessage());
        this.resultCode = resultCode;
    }

    public MinaCloudBusinessException(ResultCode resultCode, String resultMessage) {
        super(resultCode.getResultCode(), resultMessage);
        this.resultCode = resultCode;

    }

    public ResultCode getErrorCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return resultCode.getResultMessage();
        } else {
            return super.getMessage();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MinaCloudBusinessException[");
        if (java.util.Objects.nonNull(resultCode)) {
            stringBuilder.append("resultCode=").append(resultCode.getResultCode()).append(",");
            stringBuilder.append("resultMessage=").append(resultCode.getResultMessage()).append(",");
        }
        stringBuilder.append("extraMessage=").append(getMessage());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
