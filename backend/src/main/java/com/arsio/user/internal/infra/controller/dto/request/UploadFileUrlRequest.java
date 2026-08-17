package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UploadFileUrlRequest(

        @NotBlank(message = "${contentType.require}")
        String contentType,

        @NotBlank(message = "${size.require}")
        long size
) {
}
