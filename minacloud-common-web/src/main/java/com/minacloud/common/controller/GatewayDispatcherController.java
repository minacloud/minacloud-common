package com.minacloud.common.controller;


import com.minacloud.common.handler.HttpProcessHandler;
import com.minacloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class GatewayDispatcherController {
    @Autowired
    private HttpProcessHandler httpProcessHandler;

    @RequestMapping("/**/*")
    public void invoker(HttpServletRequest request, HttpServletResponse response) {
        try {
            httpProcessHandler.handler(request, response);
        } catch (Exception e) {
            LogUtils.error(log, e);
        }
    }
}
