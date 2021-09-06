package com.minacloud.common.result;

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


}
