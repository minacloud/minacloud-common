package com.minacloud.common.enums;

import com.minacloud.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultStatusEnum implements BaseEnum {
    /**
     * success
     */
    SUCCESS("S", "success"),
    /**
     * fail
     */
    FAIL("F", "fail"),
    /**
     * unknown
     */
    UNKNOWN("U", "unknown");

    private String code;
    private String description;

}
