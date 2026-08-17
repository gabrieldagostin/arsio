package com.arsio.user.internal.infra.controller.dto.response;

public record UploadFileUrlResponse(
        String uploadUrl,
        String objectKey
) {
}
