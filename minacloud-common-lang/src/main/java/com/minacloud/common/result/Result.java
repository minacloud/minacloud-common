package com.minacloud.common.result;

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
