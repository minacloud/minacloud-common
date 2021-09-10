package com.minacloud.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.minacloud.common.constant.MinaCloudConstants;
import com.minacloud.common.context.GatewayContext;
import com.minacloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpToolUtils {
    public static String getRequestData(HttpServletRequest request) throws IOException {
        InputStream inputStream = null;
        try {
            if (CharSequenceUtil.contains(request.getContentType(), MinaCloudConstants.CONTENT_TYPE_FORM_URLENCODED)) {
                return getContentFromParameterMap(request);
            } else {
                inputStream = request.getInputStream();
                Reader br = new InputStreamReader(inputStream);
                return new BufferedReader(br).lines().collect(Collectors.joining(System.lineSeparator()));
            }
        } finally {
            IoUtil.closeIfPosible(inputStream);
        }
    }

    public static void assembleResponse(HttpServletResponse response, GatewayContext context) {
        Map<String, String> headMap = context.getResponseHeaderMap();
        try {
            headMap.forEach((k, v) -> {
                if (CharSequenceUtil.isNotBlank(k) && !CharSequenceUtil.equalsIgnoreCase(k, "content-length")) {
                    response.addHeader(k, v);
                }
            });
            response.setStatus(HttpStatus.OK.value());
            response.addHeader(MinaCloudConstants.CONTENT_TYPE_HEADER, MinaCloudConstants.CONTENT_TYPE_JSON_UTF8);
            response.getWriter().write(context.getResponseBody());
        } catch (Exception e) {

            LogUtils.error(log, "exception=", e.getMessage());
        }
    }

    private static String getContentFromParameterMap(HttpServletRequest request) {
        return request.getParameterMap().
                entrySet().
                stream().
                map(entry -> {
                    String[] value = entry.getValue();
                    if (value == null) {
                        return null;
                    } else if (value.length > 1) {
                        return Arrays.stream(value).map(s -> entry.getKey() + "=" + s).collect(Collectors.joining("&"));
                    } else {
                        return entry.getKey() + "=" + value[0];
                    }
                }).filter(StrUtil::isNotBlank).collect(Collectors.joining(" & "));
    }
}

