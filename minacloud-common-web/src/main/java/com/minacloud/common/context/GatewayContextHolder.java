package com.minacloud.common.context;

public class GatewayContextHolder {
    private static final ThreadLocal<GatewayContext> GATEWAY_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static GatewayContext createGatewayContext() {
        GatewayContext context = new GatewayContext();
        GATEWAY_CONTEXT_THREAD_LOCAL.set(context);
        return context;
    }

    public static GatewayContext getGatewayContext() {
        return GATEWAY_CONTEXT_THREAD_LOCAL.get();
    }

    public static void cleanGatewayContext() {
        GATEWAY_CONTEXT_THREAD_LOCAL.remove();
    }
}
