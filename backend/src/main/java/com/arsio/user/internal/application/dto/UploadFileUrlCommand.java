package com.arsio.user.internal.application.dto;

public record UploadFileUrlCommand(
        String contentType,
        long size,
        String imageType
) {
}
