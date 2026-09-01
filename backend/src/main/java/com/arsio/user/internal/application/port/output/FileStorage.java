package com.arsio.user.internal.application.port.output;

import com.arsio.user.internal.domain.model.ObjectMetadata;

public interface FileStorage {

    String generatePresignedUploadUrl(String objectKey);

    String generatePresignedDownloadUrl(String objectKey);

    ObjectMetadata getObjectMetadata(String objectKey);
}
