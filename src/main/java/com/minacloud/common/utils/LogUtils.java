package com.minacloud.common.utils;


import com.minacloud.common.constant.MinaCloudConstants;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogUtils {
    private LogUtils() {
    }

    public static final String SEP = ",";
    private static final char RIGHT_TAG = ']';
    private static final char LEFT_TAG = '[';

    public static void info(Logger logger, Object... objs) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objs));
        }
    }

    public static void info(Logger logger, Throwable e, Object... objs) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objs), e);
        }
    }

    public static void warn(Logger logger, Object... objs) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(objs));
        }
    }

    public static void warn(Logger logger, Throwable e, Object... objs) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(objs), e);
        }
    }

    public static void error(Logger logger, Object... objs) {
        if (logger.isErrorEnabled()) {
            logger.error(getLogString(objs));
        }
    }

    public static void error(Logger logger, Throwable e, Object... objs) {
        if (logger.isErrorEnabled()) {
            logger.error(getLogString(objs), e);
        }
    }

    public static void debug(Logger logger, Object... objs) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objs));
        }
    }

    public static void debug(Logger logger, Throwable e, Object... objs) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objs), e);
        }
    }

    public static String getLogString(Object... objs) {
        StringBuilder log = new StringBuilder();
        log.append(LEFT_TAG);
        log.append(fetchTraceId());
        log.append(SEP);
        log.append(fetchTimeMillis());
        log.append(SEP);
        log.append(RIGHT_TAG);
        log.append(SEP);
        if (objs != null) {
            for (Object o : objs) {
                log.append(o);
            }
        }
        return log.toString();
    }

    private static String fetchTimeMillis() {
        return LocalDateTime.now().format(MinaCloudConstants.DEFAULT_DATE_TIME_FORMATTER);
    }

    private static String fetchTraceId() {
        String traceId = TracerUtils.getTraceId();
        if (Objects.isNull(traceId) || Objects.equals(traceId, "")) {
            traceId = String.valueOf(Thread.currentThread().getId());
        }
        return traceId;
    }
}
