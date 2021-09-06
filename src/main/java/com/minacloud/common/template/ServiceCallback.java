package com.minacloud.common.template;


import com.minacloud.common.result.ResultCode;

public interface ServiceCallback<T> {

    /**
     * check the input parameters
     */
    void checkParameter();

    /**
     * performing member processing
     *
     * @return business result
     */
    T process();

    /**
     * build fail result
     *
     * @param resultCode result body
     * @param errorMsg   error msg
     * @return T error result
     */
    T buildFailureResult(ResultCode resultCode, String errorMsg);

    void buildSuccessResult(T response);
}