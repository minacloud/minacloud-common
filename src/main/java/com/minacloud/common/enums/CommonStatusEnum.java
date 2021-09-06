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
package com.minacloud.common.enums;

import com.minacloud.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>CommonStatusEnum class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements BaseEnum {

    /**
     * 正常
     */
    NORMAL("0", "正常"),
    /**
     * 禁用
     */
    DISABLED("1", "禁用");

    private String code;
    /**
     * desc
     */
    private String description;

}
