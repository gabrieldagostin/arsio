package com.arsio.user.internal.infra.controller.dto.response;

public record UploadAvatarUrlResponse(
        String uploadUrl,
        String objectKey
) {
}
