package com.minacloud.common.context;

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
