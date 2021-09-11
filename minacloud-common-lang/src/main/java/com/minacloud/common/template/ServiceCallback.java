package com.minacloud.common.template;


import com.minacloud.common.result.ResultCode;

public interface ServiceCallback<R> {

    /**
     * check the input parameters
     */
    void checkParameter();

    /**
     * performing member processing
     *
     * @return business result
     */
    R process();

    /**
     * build fail result
     *
     * @param resultCode result body
     * @param errorMsg   error msg
     * @return T error result
     */
    R buildFailureResult(ResultCode resultCode, String errorMsg);

    void buildSuccessResult(R response);

}