package com.minacloud.common.result;

import com.minacloud.common.enums.DefaultResultCodeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class Result implements ResultCode {
    private String resultCode;

    private String resultMessage;

    private String resultStatus;


    public static Result of(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode.getResultCode());
        result.setResultStatus(resultCode.getResultStatus());
        result.setResultMessage(resultCode.getResultMessage());
        return result;
    }

    public static Result of(ResultCode resultCode, String resultMessage) {
        Result result = new Result();
        result.setResultCode(resultCode.getResultCode());
        result.setResultStatus(resultCode.getResultStatus());
        result.setResultMessage(resultMessage);
        return result;
    }

    public static Result of(String resultCode) {
        Result result = new Result();
        DefaultResultCodeEnum byResultCode = DefaultResultCodeEnum.getByResultCode(resultCode);
        result.setResultCode(byResultCode.getResultCode());
        result.setResultStatus(byResultCode.getResultStatus());
        return result;
    }

    public static Result of(String resultCode, String resultStatus) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultStatus(resultStatus);
        return result;
    }

    public static Result of(String resultCode, String resultStatus, String resultMessage) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultStatus(resultStatus);
        result.setResultMessage(resultMessage);
        return result;
    }
}
