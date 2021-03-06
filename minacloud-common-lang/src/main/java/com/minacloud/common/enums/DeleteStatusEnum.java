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

import com.minacloud.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>DeleteStatusEnum class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
@Getter
@AllArgsConstructor
public enum DeleteStatusEnum implements BaseEnum {

    /**
     * 正常
     */
    NORMAL("0", "正常"),
    /**
     * 已删除
     */
    DELETED("1", "已删除");
    /**
     * code
     */

    private String code;
    /**
     * desc
     */
    private String description;

}

