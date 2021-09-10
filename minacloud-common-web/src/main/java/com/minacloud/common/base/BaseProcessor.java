package com.minacloud.common.base;

import com.minacloud.common.utils.JsonUtil;

import java.lang.reflect.ParameterizedType;

public abstract class BaseProcessor<P extends BaseRequest, R extends BaseResponse> {

    public abstract void checkParameter(P request);

    public abstract P process(R request);

    public P convert(Object request) {
        Class<P> rawType = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return JsonUtil.parseObject(request.toString(), rawType);
    }
}
