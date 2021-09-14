package com.minacloud.common.constant;

/*-
 * #%L
 * minacloud-common-lang
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

import java.time.format.DateTimeFormatter;

public interface MinaCloudConstants {
    String RESULT_KEY = "result";

    /**
     * BUFFER_SIZE
     */
    int BUFFER_SIZE = 1024;
    /**
     * CONTENT_TYPE_FORM_URLENCODED
     */
    String CONTENT_TYPE_HEADER = "content-type";
    String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    String CONTENT_TYPE_FORM_URLENCODED_UTF8 = "application/x-www-form-urlencoded; charset=utf-8";
    /**
     * CONTENT_TYPE_JSON
     */
    String CONTENT_TYPE_JSON = "application/json";
    String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";
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
