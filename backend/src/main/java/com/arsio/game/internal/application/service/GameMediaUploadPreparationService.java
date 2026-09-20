package com.arsio.game.internal.application.service;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.shared.storage.UploadFileUrlResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GameMediaUploadPreparationService {

    private final GameMediaStorage fileStorage;
    private String objectKey;

    public GameMediaUploadPreparationService(GameMediaStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public UploadFileUrlResponse execute(UUID id, UploadFileUrlCommand command) {

        GameId gameId = new GameId(id);

        String extension = getExtension(command.contentType());

        if ("THUMBNAIL".equals(command.imageType())) {
            objectKey = String.format(
                    "thumbnails/%s/%s%s",
                    gameId.value(),
                    UUID.randomUUID(),
                    extension
            );
        }

        if ("BANNER".equals(command.imageType())) {
            objectKey = String.format(
                    "banners/%s/%s%s",
                    gameId.value(),
                    UUID.randomUUID(),
                    extension
            );
        }

        if ("SCREENSHOT".equals(command.imageType())) {
            objectKey = String.format(
                    "screenshots/%s/%s%s",
                    gameId.value(),
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
