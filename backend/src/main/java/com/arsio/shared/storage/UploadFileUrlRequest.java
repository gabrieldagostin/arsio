package com.arsio.shared.storage;

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
        String imageType,

        String external_url
) {
}
