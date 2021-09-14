package com.minacloud.common.exception;

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
