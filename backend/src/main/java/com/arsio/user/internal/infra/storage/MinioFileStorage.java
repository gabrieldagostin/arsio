package com.arsio.user.internal.infra.storage;

import com.arsio.shared.properties.MinioProperties;
import com.arsio.user.internal.application.port.output.FileStorage;
import com.arsio.user.internal.domain.model.record.ObjectMetadata;
import io.minio.*;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioFileStorage implements FileStorage {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public String generatePresignedUploadUrl(String objectKey) {

        try {
            String uploadUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.PUT)
                            .bucket(minioProperties.getUserFilesBucket())
                            .object(objectKey)
                            .expiry(15, TimeUnit.MINUTES)
                            .build()
            );

            return uploadUrl;

        } catch (MinioException e) {
            throw new RuntimeException("Failed to generate presigned URL", e);
        }
    }

    @Override
    public String generatePresignedDownloadUrl(String objectKey) {

        try {
            String uploadUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(minioProperties.getUserFilesBucket())
                            .object(objectKey)
                            .expiry(15, TimeUnit.MINUTES)
                            .build()
            );

            return uploadUrl;

        } catch (MinioException e) {
            throw new RuntimeException("Failed to generate presigned URL", e);
        }
    }

    @Override
    public ObjectMetadata getObjectMetadata(String objectKey) {

        try {

            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(minioProperties.getUserFilesBucket())
                            .object(objectKey)
                            .build()
            );

            return new ObjectMetadata(
                    stat.size(),
                    stat.contentType()
            );

        }catch (MinioException e) {
            throw new RuntimeException("Failed to retrieve object metadata", e);
        }
    }
}
