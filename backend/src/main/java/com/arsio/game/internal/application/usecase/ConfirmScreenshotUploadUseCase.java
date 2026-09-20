package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.game.internal.infra.Controller.dto.response.GetScreenshotResponse;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.shared.storage.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ConfirmScreenshotUploadUseCase {

    private static final long MAX_AVATAR_SIZE = 5 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png",
            "image/jpeg",
            "image/webp"
    );

    private final GameMediaStorage fileStorage;
    private final GameMediaRepository gameMediaRepository;

    public ConfirmScreenshotUploadUseCase(GameMediaStorage fileStorage, GameMediaRepository gameMediaRepository) {
        this.fileStorage = fileStorage;
        this.gameMediaRepository = gameMediaRepository;
    }

    public GetScreenshotResponse execute(UUID id, ConfirmFileUploadCommand command) {

        GameId gameId = new GameId(id);

        ObjectMetadata metadata = fileStorage.getObjectMetadata(command.objectKey().value());

        if (metadata.size() > MAX_AVATAR_SIZE) {
            throw new IllegalArgumentException(
                    "Screenshot cannot exceed 5 MB"
            );
        }

        if (!ALLOWED_TYPES.contains(metadata.contentType())) {
            throw new IllegalArgumentException(
                    "Unsupported screenshot format"
            );
        }

        GameMedia gameMedia = GameMedia.create(
                gameId,
                command.objectKey(),
                MediaType.IMAGE
        );

        gameMediaRepository.save(gameMedia);

        String screenshotUrl = fileStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

        return new GetScreenshotResponse(screenshotUrl);

    }
}
