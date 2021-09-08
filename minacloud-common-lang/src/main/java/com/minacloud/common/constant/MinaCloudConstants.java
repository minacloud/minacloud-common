package com.minacloud.common.constant;

import java.time.format.DateTimeFormatter;

public interface MinaCloudConstants {

    /**
     * BUFFER_SIZE
     */
    int BUFFER_SIZE = 1024;
    /**
     * CONTENT_TYPE_FORM_URLENCODED
     */
    String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    /**
     * CONTENT_TYPE_JSON
     */
    String CONTENT_TYPE_JSON = "application/json";
    /**
     * Constant <code>DATE_YYYY_MM_DD="yyyy-MM-dd"</code>
     */
    String DEFAULT_DATE = "yyyy-MM-dd";
    /**
     * Constant <code>DEFAULT_DATE_FORMATTER="yyyy-MM-dd"</code>
     */
    DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE);
    /**
     * Constant <code>DEFAULT_DATE_TIME="yyyy-MM-dd HH:mm:ss"</code>
     */
    String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * Constant <code>DEFAULT_DATE_TIME_FORMATTER="yyyy-MM-dd HH:mm:ss"</code>
     */
    DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME);

    /**
     * Constant <code>DEFAULT_PASSWORD="123456"</code>
     */
    String DEFAULT_PASSWORD = "123456";
}
