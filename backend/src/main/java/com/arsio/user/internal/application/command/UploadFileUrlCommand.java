package com.arsio.user.internal.application.command;

public record UploadFileUrlCommand(
        String contentType,
        long size,
        String imageType
) {
}
