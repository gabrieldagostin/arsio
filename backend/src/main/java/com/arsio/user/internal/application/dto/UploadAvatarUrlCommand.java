package com.arsio.user.internal.application.dto;

public record UploadAvatarUrlCommand(
        String contentType,
        long size
) {
}
