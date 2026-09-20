package com.arsio.shared.storage;

public record UploadFileUrlResponse(
        String uploadUrl,
        String objectKey
) {
}
