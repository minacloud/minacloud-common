package com.minacloud.common.base;

import com.minacloud.common.result.Result;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lise
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class BaseResponse implements Serializable {

    @NotNull
    private Result result;
}
