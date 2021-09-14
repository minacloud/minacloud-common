package com.minacloud.common.result;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface ResultCode {
    @NotBlank
    @Size(max = 64)
    String getResultCode();

    @NotBlank
    @Size(max = 256)
    String getResultMessage();

    @NotBlank
    @Size(max = 2)
    String getResultStatus();


}
