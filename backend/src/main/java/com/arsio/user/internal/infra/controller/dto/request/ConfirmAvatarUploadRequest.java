package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ConfirmAvatarUploadRequest(
        @NotBlank(message = "${objectKey.require}")
        String objectKey
) {
}
