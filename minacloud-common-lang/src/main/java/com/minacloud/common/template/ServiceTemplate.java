package com.minacloud.common.template;

import com.minacloud.common.base.BaseResponse;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.exception.MinaCloudParamIllegalException;
import com.minacloud.common.result.ResultCode;
import com.minacloud.common.utils.JsonUtil;
import com.minacloud.common.utils.LogUtils;
import com.minacloud.common.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import java.util.Locale;
import java.util.Set;

@Slf4j
public class ServiceTemplate {
    /**
     * member processing templates
     *
     * @param request  request parameters
     * @param scenario business scenario event code
     * @param callback member callback template
     */
    public static <P, R> R execute(P request, String scenario, ServiceCallback< R> callback) {

        R response = null;
        try {
            // print invocation request log
            pointInvocationRequestLog(scenario, request);

            // parameter check
            parameterValidator(request, callback);

            // member process
            response = callback.process();

            // fill success response
            callback.buildSuccessResult(response);

        } catch (MinaCloudBusinessException e) {
            LogUtils.warn(log, e, request);
            ResultCode errorCode = e.getErrorCode();
            response = callback.buildFailureResult(errorCode, e.getMessage());
        } catch (Throwable e) {
            LogUtils.error(log, e, request);
            response = callback.buildFailureResult(DefaultResultCodeEnum.UNKNOWN, e.getMessage());

        } finally {
            if (response instanceof BaseResponse) {
                // print biz log
                pointInvocationResponseLog(scenario, (BaseResponse) response);
            }
        }
        return response;
    }

    /**
     * print invocation request log
     */
    private static void pointInvocationRequestLog(String scenario, Object args) {
        LogUtils.info(log, scenario, " invoke params:", JsonUtil.toJsonString(args));
    }

    /**
     * print invocation response log
     */
    private static <R, T> void pointInvocationResponseLog(String scenario, BaseResponse result) {
        LogUtils.info(log, scenario, " invoke result:", JsonUtil.toJsonString(result));

    }

    /**
     * parameter validatorRefundProcessor
     */
    private static <U> void parameterValidator(U request, ServiceCallback callback) {
        // parameter check
        Locale.setDefault(new Locale("en"));

        Set<ConstraintViolation<U>> constraintViolations;
        try {
            constraintViolations = ValidatorUtils.getValidator().validate(request);
        } catch (IllegalArgumentException e) {
            throw new MinaCloudParamIllegalException(e.getMessage());
        }

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<U> error = constraintViolations.iterator().next();
            throw new MinaCloudParamIllegalException(error.getPropertyPath() + " " + error.getMessage());
        }
        // customize parameter check
        callback.checkParameter();
    }

}

