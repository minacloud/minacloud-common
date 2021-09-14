/*
 * Copyright © 2021 Laysan (lslvxy@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minacloud.common.utils;

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

import cn.hutool.core.util.IdUtil;

/**
 * <p>IdUtil class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
public class IdUtils extends IdUtil {
    /**
     * <p>nextSimpleId.</p>
     *
     * @return a {@link Long} object.
     */
    public static Long nextSimpleId() {
        return getSnowflake(1, 1).nextId();
    }

    /**
     * <p>nextSimpleIdStr.</p>
     *
     * @return a {@link String} object.
     */
    public static String nextSimpleIdStr() {
        return getSnowflake(1, 1).nextIdStr();
    }

    /**
     * <p>nextId.</p>
     *
     * @return a {@link Long} object.
     */
    public static Long nextId(long workerId, long datacenterId) {
        return getSnowflake(workerId, datacenterId).nextId();
    }

    /**
     * <p>nextIdStr.</p>
     *
     * @return a {@link String} object.
     */
    public static String nextIdStr(long workerId, long datacenterId) {
        return getSnowflake(workerId, datacenterId).nextIdStr();
    }
}
