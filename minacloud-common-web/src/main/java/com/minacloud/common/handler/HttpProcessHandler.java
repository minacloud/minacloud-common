package com.minacloud.common.handler;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import com.minacloud.common.context.GatewayContext;
import com.minacloud.common.context.GatewayContextHolder;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.result.Result;
import com.minacloud.common.util.HttpToolUtils;
import com.minacloud.common.utils.JsonUtil;
import com.minacloud.common.utils.LogUtils;
import com.minacloud.common.utils.TracerUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpProcessHandler {
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        String requestData = null;
        GatewayContext context = GatewayContextHolder.createGatewayContext();
        try {
            String traceIdHeader = request.getHeader(TracerUtils.HEADER_TRACEID);
            if (CharSequenceUtil.isBlank(traceIdHeader)) {
                traceIdHeader = IdUtil.fastSimpleUUID();
                TracerUtils.setTraceId(traceIdHeader);
            }
            String traceId = TracerUtils.getTraceId();
            context.setTraceId(traceId);
            context.setRequestUri(request.getRequestURI());
            context.setRequestUrl(request.getRequestURL().toString());
            context.setRequestMethod(request.getMethod());
            context.setContextPath(request.getContextPath());
            context.setServletPath(request.getServletPath());
            context.setServerPort(request.getServerPort());
            context.setScheme(request.getScheme());
            context.setServerName(request.getServerName());

            MDC.put(TracerUtils.HEADER_TRACEID, traceId);
            requestData = HttpToolUtils.getRequestData(request);

        } catch (MinaCloudBusinessException e) {
            context.setBusinessException(e);
            handException(context);
        } catch (Throwable e) {
            MinaCloudBusinessException exception = new MinaCloudBusinessException(DefaultResultCodeEnum.UNKNOWN, e.getMessage());
            context.setBusinessException(exception);
            handException(context);
        } finally {
            HttpToolUtils.assembleResponse(response, context);
            MDC.clear();
            long end = System.currentTimeMillis();
            LogUtils.info(log, "[request= " + requestData + "],", "[time_consume=" + (end - start) + "]");
        }
    }

    private void handException(GatewayContext context) {
        LogUtils.error(log, "http process error");
        MinaCloudBusinessException businessException = context.getBusinessException();
        Result error = Result.of(businessException.getErrorCode());
        context.setResponseBody(JsonUtil.toJsonString(error));
    }
}
