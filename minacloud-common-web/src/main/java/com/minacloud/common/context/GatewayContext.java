package com.minacloud.common.context;

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

import com.minacloud.common.exception.MinaCloudBusinessException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class GatewayContext {
    private String traceId;
    private String requestUri;
    private String requestUrl;
    private String requestMethod;
    private String contextPath;
    private String servletPath;
    private int serverPort;
    private String scheme;
    private String serverName;
    private Map<String, String> requestHeaderMap = new HashMap<>();
    private Map<String, String> responseHeaderMap = new HashMap<>();
    private String responseBody;
    private String requestBody;
    private MinaCloudBusinessException businessException;

}
