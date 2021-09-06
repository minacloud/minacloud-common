package com.minacloud.common.result;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface ResultCode {
    @NotBlank
    @Size(max = 64)
    String getResultCode();

    @NotBlank
    @Size(max = 256)
    String getResultMessage();

    @NotBlank
    @Size(max = 2)
    String getResultStatus();


}
