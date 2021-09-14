package com.minacloud.common.base;

/*-
 * #%L
 * minacloud-common-web
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

import com.minacloud.common.utils.JsonUtil;

import java.lang.reflect.ParameterizedType;

public abstract class BaseProcessor<P extends BaseRequest, R extends BaseResponse> {

    public abstract void checkParameter(P request);

    public abstract R process(P request);

    public P convert(Object request) {
        Class<P> rawType = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return JsonUtil.parseObject(request.toString(), rawType);
    }
}
