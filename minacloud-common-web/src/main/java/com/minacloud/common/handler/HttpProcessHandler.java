package com.minacloud.common.handler;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import com.minacloud.common.base.BaseProcessor;
import com.minacloud.common.base.BaseRequest;
import com.minacloud.common.base.BaseResponse;
import com.minacloud.common.context.GatewayContext;
import com.minacloud.common.context.GatewayContextHolder;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.manage.ProcessorManager;
import com.minacloud.common.result.Result;
import com.minacloud.common.result.ResultCode;
import com.minacloud.common.template.ServiceCallback;
import com.minacloud.common.template.ServiceTemplate;
import com.minacloud.common.util.HttpToolUtils;
import com.minacloud.common.utils.JsonUtil;
import com.minacloud.common.utils.LogUtils;
import com.minacloud.common.utils.TracerUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpProcessHandler {
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        GatewayContext context = GatewayContextHolder.createGatewayContext();
        try {
            String traceIdHeader = request.getHeader(TracerUtils.HEADER_TRACEID);
            if (CharSequenceUtil.isBlank(traceIdHeader)) {
                traceIdHeader = IdUtil.fastSimpleUUID();
                TracerUtils.setTraceId(traceIdHeader);
            }
            String traceId = TracerUtils.getTraceId();
            context.setTraceId(traceId);
            String requestURI = request.getRequestURI();
            context.setRequestUri(requestURI);
            context.setRequestUrl(request.getRequestURL().toString());
            context.setRequestMethod(request.getMethod());
            context.setContextPath(request.getContextPath());
            context.setServletPath(request.getServletPath());
            context.setServerPort(request.getServerPort());
            context.setScheme(request.getScheme());
            context.setServerName(request.getServerName());

            MDC.put(TracerUtils.HEADER_TRACEID, traceId);
            String requestBody = HttpToolUtils.getRequestData(request);
            context.setRequestBody(requestBody);
            String action = CharSequenceUtil.removePrefix(requestURI, "/");

            BaseProcessor<BaseRequest, BaseResponse> processor = ProcessorManager.getProcessorByAction(action);
            if (java.util.Objects.isNull(processor)) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.RES_NOT_FOUND, "Action Not Found");
            }
            BaseResponse baseResponse = ServiceTemplate.execute(context, action, new ServiceCallback<BaseResponse>() {
                @Override
                public void checkParameter() {
                    BaseRequest convert = processor.convert(context.getRequestBody());
                    processor.checkParameter(convert);
                }

                @Override
                public BaseResponse process() {
                    return null;
                }

                @Override
                public BaseResponse buildFailureResult(ResultCode resultCode, String errorMsg) {
                    return null;
                }

                @Override
                public void buildSuccessResult(BaseResponse response) {

                }
            });
            context.setResponseBody(JsonUtil.toJsonString(baseResponse));

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
            LogUtils.info(log, "time_consume: " + (end - start));
        }
    }

    private void handException(GatewayContext context) {
        LogUtils.error(log, "http process error");
        MinaCloudBusinessException businessException = context.getBusinessException();
        Result error = Result.of(businessException.getErrorCode());
        context.setResponseBody(JsonUtil.toJsonString(error));
    }
}
