package com.arsio.user.internal.application.command;

public record UploadFileUrlCommand(
        String contentType,
        Long size,
        String imageType
) {
}
