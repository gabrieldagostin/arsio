package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.game.internal.infra.Controller.dto.response.GetBannerResponse;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.shared.storage.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ConfirmBannerUploadUseCase {

    private static final long MAX_BANNER_SIZE = 10 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png",
            "image/jpeg",
            "image/webp"
    );

    private final GameMediaStorage fileStorage;
    private final GameMediaRepository gameMediaRepository;

    public ConfirmBannerUploadUseCase(GameMediaStorage fileStorage, GameMediaRepository gameMediaRepository) {
        this.fileStorage = fileStorage;
        this.gameMediaRepository = gameMediaRepository;
    }

    public GetBannerResponse execute(UUID id, ConfirmFileUploadCommand command) {

        GameId gameId = new GameId(id);

        ObjectMetadata metadata = fileStorage.getObjectMetadata(command.objectKey().value());

        if (metadata.size() > MAX_BANNER_SIZE) {
            throw new IllegalArgumentException(
                    "Banner cannot exceed 10 MB"
            );
        }

        if (!ALLOWED_TYPES.contains(metadata.contentType())) {
            throw new IllegalArgumentException(
                    "Unsupported banner format"
            );
        }

        GameMedia gameMedia = GameMedia.create(
                gameId,
                command.objectKey(),
                MediaType.IMAGE,
                MediaRole.BANNER
        );

        gameMediaRepository.save(gameMedia);

        String bannerUrl = fileStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

        return new GetBannerResponse(bannerUrl);
    }
}
