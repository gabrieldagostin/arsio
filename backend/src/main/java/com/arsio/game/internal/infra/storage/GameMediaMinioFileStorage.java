package com.arsio.game.internal.infra.storage;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.shared.properties.MinioProperties;
import com.arsio.shared.storage.ObjectMetadata;
import io.minio.*;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GameMediaMinioFileStorage implements GameMediaStorage {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public String generatePresignedUploadUrl(String objectKey) {

        try {

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.PUT)
                            .bucket(minioProperties.getGameMediaBucket())
                            .object(objectKey)
                            .expiry(15, TimeUnit.MINUTES)
                            .build()
            );

        } catch (MinioException e) {
            throw new RuntimeException("Failed to generate presigned URL", e);
        }
    }

    @Override
    public String generatePresignedDownloadUrl(String objectKey) {

        try {

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(minioProperties.getGameMediaBucket())
                            .object(objectKey)
                            .expiry(15, TimeUnit.MINUTES)
                            .build()
            );

        } catch (MinioException e) {
            throw new RuntimeException("Failed to generate presigned URL", e);
        }
    }

    @Override
    public ObjectMetadata getObjectMetadata(String objectKey) {

        try {

            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(minioProperties.getGameMediaBucket())
                            .object(objectKey)
                            .build()
            );

            return new ObjectMetadata(
                    stat.size(),
                    stat.contentType()
            );

        } catch (MinioException e) {
            throw new RuntimeException("Failed to retrieve object metadata", e);
        }
    }

    @Override
    public void delete(String objectKey) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioProperties.getGameMediaBucket())
                            .object(objectKey)
                            .build()
            );
        } catch (MinioException e) {
            throw new RuntimeException("Failed to delete object", e);
        }
    }
}
