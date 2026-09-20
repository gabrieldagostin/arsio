package com.arsio.user.internal.application.service;

import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.shared.storage.UploadFileUrlResponse;
import com.arsio.user.internal.application.port.output.UserFileStorage;
import com.arsio.user.internal.domain.valueobject.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserFileUploadPreparationService {

    private final UserFileStorage fileStorage;
    private String objectKey;

    public UserFileUploadPreparationService(UserFileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public UploadFileUrlResponse execute(UUID id, UploadFileUrlCommand command) {

        UserId userId = new UserId(id);

        String extension = getExtension(command.contentType());

        if ("AVATAR".equals(command.imageType())) {
            objectKey = String.format(
                    "avatars/%s/%s%s",
                    userId.value(),
                    UUID.randomUUID(),
                    extension
            );
        }

        if ("BANNER".equals(command.imageType())) {
            objectKey = String.format(
                    "banners/%s/%s%s",
                    userId.value(),
                    UUID.randomUUID(),
                    extension
            );
        }

        String uploadUrl = fileStorage.generatePresignedUploadUrl(objectKey);

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
