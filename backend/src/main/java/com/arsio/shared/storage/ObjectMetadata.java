package com.arsio.shared.storage;

public record ObjectMetadata(
        Long size,
        String contentType
) {
}
