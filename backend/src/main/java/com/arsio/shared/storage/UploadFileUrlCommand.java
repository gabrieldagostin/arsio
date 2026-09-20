package com.arsio.shared.storage;

public record UploadFileUrlCommand(
        String contentType,
        Long size,
        String imageType
) {
}
