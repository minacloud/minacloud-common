package com.minacloud.common.handler;

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

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Response;
import com.minacloud.common.base.BaseProcessor;
import com.minacloud.common.context.FileRequestWrapper;
import com.minacloud.common.context.WebRuntimeContext;
import com.minacloud.common.context.WebRuntimeContextHolder;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.manage.ProcessorManager;
import com.minacloud.common.result.ResultCode;
import com.minacloud.common.template.ServiceCallback;
import com.minacloud.common.template.ServiceTemplate;
import com.minacloud.common.tracer.TracerUtil;
import com.minacloud.common.util.HttpToolUtils;
import com.minacloud.common.utils.JsonUtil;
import com.minacloud.common.utils.LogUtils;
import io.opentracing.Span;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HttpProcessHandler {
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        WebRuntimeContext context = WebRuntimeContextHolder.createWebRuntimeContext();
        try {
            Span span = TracerUtil.getSpan();
            if (ObjectUtil.isNotNull(span)) {
                context.setTraceId(TracerUtil.getTracerId());
            } else {
                Span webroot = TracerUtil.createSpan("webroot");
                TracerUtil.putSpan(webroot);
                TracerUtil.logTraceId(webroot);
                context.setTraceId(TracerUtil.getTracerId());
            }

            String requestURI = request.getRequestURI();
            context.setRequestUri(requestURI);
            context.setRequestUrl(request.getRequestURL().toString());
            context.setMethod(request.getMethod());
            context.setContextPath(request.getContextPath());
            context.setServletPath(request.getServletPath());
            context.setServerPort(request.getServerPort());
            context.setScheme(request.getScheme());
            context.setServerName(request.getServerName());

            String requestBody = HttpToolUtils.getRequestData(request);
            context.setRequestBody(requestBody);
            String action = CharSequenceUtil.removePrefix(requestURI, "/");
            if (request instanceof MultipartHttpServletRequest) {
                Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
                List<FileRequestWrapper> fileList = fileMap.values().stream().map(FileRequestWrapper::new).collect(Collectors.toList());
                context.setFiles(fileList);
            }

            BaseProcessor<Command, Response> processor = ProcessorManager.getProcessorByAction(action);
            if (java.util.Objects.isNull(processor)) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.RES_NOT_FOUND, "Action Not Found");
            }
            Response baseResponse = ServiceTemplate.execute(context, action, new ServiceCallback<Response>() {
                Command request = processor.convert(context.getRequestBody());

                @Override
                public void checkParameter() {
                    processor.checkParameter(request);
                }

                @Override
                public Response process() {
                    return processor.process(request);
                }

                @Override
                public Response buildFailureResult(ResultCode resultCode, String errorMsg) {
                    throw new MinaCloudBusinessException(resultCode, errorMsg);
                }

                @Override
                public void buildSuccessResult(Response response) {
                    if (ObjectUtil.isNull(response)) {
                        response = Response.buildSuccess();
                    }
                    response.setSuccess(true);
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

    private void handException(WebRuntimeContext context) {
        LogUtils.error(log, "http process error");
        MinaCloudBusinessException businessException = context.getBusinessException();
        String message = businessException.getMessage();
        ResultCode errorCode = businessException.getErrorCode();
        Response response = Response.buildFailure(errorCode.getResultCode(), message);
        context.setResponseBody(JsonUtil.toJsonString(response));
    }
}
