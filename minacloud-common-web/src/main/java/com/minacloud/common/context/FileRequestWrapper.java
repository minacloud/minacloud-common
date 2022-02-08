package com.minacloud.common.context;

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


import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileRequestWrapper {

    private InputStream inputStream;

    private String name;

    private String originalFilename;

    private String contentType;

    private byte[] bytes = new byte[0];


    public FileRequestWrapper() {
    }

    public FileRequestWrapper(MultipartFile file) {
        try {
            this.inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "InputStream Read Error");
        }
        this.name = file.getName();
        this.originalFilename = file.getOriginalFilename();
        this.contentType = file.getContentType();
    }

    public byte[] getBytes() {
        if (bytes != null) {
            return Arrays.copyOf(bytes, bytes.length);
        }
        if (inputStream != null) {
            try {
                bytes = FileCopyUtils.copyToByteArray(inputStream);
                return Arrays.copyOf(bytes, bytes.length);
            } catch (IOException e) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "InputStream Read Error");
            }
        }
        return new byte[0];
    }

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        if (inputStream != null) {
            return inputStream;
        }
        if (bytes != null) {
            return new ByteArrayInputStream(bytes);
        }
        return null;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }
}
