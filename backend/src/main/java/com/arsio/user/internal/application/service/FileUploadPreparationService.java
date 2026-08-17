package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.dto.UploadFileUrlCommand;
import com.arsio.user.internal.application.port.output.FileStorage;
import com.arsio.user.internal.infra.controller.dto.response.UploadFileUrlResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class FileUploadPreparationService {

    private final FileStorage fileStorage;
    private String objectKey;

    public FileUploadPreparationService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public UploadFileUrlResponse execute(UUID userId, UploadFileUrlCommand command) {

        String extension = getExtension(command.contentType());

        if (command.imageType().equals("AVATAR")) {
            objectKey = String.format(
                    "avatars/%s/%s%s",
                    userId,
                    UUID.randomUUID(),
                    extension
            );
        }

        if (command.imageType().equals("BANNER")) {
            objectKey = String.format(
                    "banners/%s/%s%s",
                    userId,
                    UUID.randomUUID(),
                    extension
            );
        }

        String uploadUrl = fileStorage.generatePresignedUrl(objectKey);

        return new UploadFileUrlResponse(
                uploadUrl,
                objectKey
        );
    }

    private String getExtension(String contentType) {
        return switch (contentType) {
            case "image/png" -> ".png";
            case "image/jpeg" -> ".jpg";
            case "image/webp" -> ".webp";
            default -> throw new IllegalArgumentException(
                    "Unsupported image type: " + contentType
            );
        };
    }
}
