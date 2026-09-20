package com.arsio.shared.storage;

public interface FileStorage {

    String generatePresignedUploadUrl(String objectKey);

    String generatePresignedDownloadUrl(String objectKey);

    ObjectMetadata getObjectMetadata(String objectKey);
}
