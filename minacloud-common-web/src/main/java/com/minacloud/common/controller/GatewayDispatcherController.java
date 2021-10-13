package com.minacloud.common.controller;

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


import com.minacloud.common.handler.HttpProcessHandler;
import com.minacloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class GatewayDispatcherController {
    @Autowired
    private HttpProcessHandler httpProcessHandler;

    @RequestMapping(value = "/api/**/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receiver(HttpServletRequest request, HttpServletResponse response) {
        try {
            httpProcessHandler.handler(request, response);
        } catch (Exception e) {
            LogUtils.error(log, e);
        }
    }

    @RequestMapping(value = "/api/**/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void doUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
        try {
            httpProcessHandler.handler(request, response);
        } catch (Exception e) {
            LogUtils.error(log, e);
        }
    }

    @RequestMapping("/index.html")
    public void renderIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("Welcome!!!");
        } catch (IOException e) {
            LogUtils.error(log, e);
        }
    }
}
