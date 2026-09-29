package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameMediaResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetGameMediaUseCase {

    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetGameMediaUseCase(GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public GetGameMediaResponse execute(UUID value) {

        GameId gameId = new GameId(value);

        Set<GameMedia> gameMedia = gameMediaRepository.findAllByGameId(gameId);

        String thumbnailUrl = gameMedia.stream()
                .filter(media -> media.getRole() == MediaRole.THUMBNAIL)
                .findFirst()
                .map(media -> gameMediaStorage.generatePresignedDownloadUrl(
                        media.getObjectKey().value()
                ))
                .orElse(null);

        String bannerUrl = gameMedia.stream()
                .filter(media -> media.getRole() == MediaRole.BANNER)
                .findFirst()
                .map(media -> gameMediaStorage.generatePresignedDownloadUrl(
                        media.getObjectKey().value()
                ))
                .orElse(null);

        Set<String> screenshotsUrl = gameMedia.stream()
                .filter(media -> media.getRole() == MediaRole.SCREENSHOT)
                .map(media -> gameMediaStorage.generatePresignedDownloadUrl(
                        media.getObjectKey().value()
                ))
                .collect(Collectors.toSet());

        String trailerUrl = gameMedia.stream()
                .filter(media -> media.getRole() == MediaRole.TRAILER)
                .findFirst()
                .map(media -> media.getExternalUrl().value())
                .orElse(null);

        return new GetGameMediaResponse(
                thumbnailUrl,
                bannerUrl,
                screenshotsUrl,
                trailerUrl
        );
    }
}
