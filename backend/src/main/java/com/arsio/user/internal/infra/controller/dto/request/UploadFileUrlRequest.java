package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UploadFileUrlRequest(

        @NotBlank(message = "{contentType.require}")
        String contentType,

        @NotNull(message = "{size.require}")
        @Positive
        Long size,

        @NotBlank(message = "{imageType.require}")
        String imageType
) {
}
