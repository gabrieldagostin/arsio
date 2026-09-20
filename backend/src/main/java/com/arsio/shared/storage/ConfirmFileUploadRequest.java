package com.arsio.shared.storage;

import jakarta.validation.constraints.NotBlank;

public record ConfirmFileUploadRequest(

        @NotBlank(message = "{objectKey.require}")
        String objectKey
) {
}
