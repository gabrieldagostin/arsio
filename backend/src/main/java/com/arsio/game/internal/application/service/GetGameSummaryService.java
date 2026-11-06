package com.arsio.game.internal.application.service;

import com.arsio.game.api.dto.GameSummary;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetGameSummaryService {

    private final GameRepository games;
    private final GameMediaRepository gameMedia;
    private final GameMediaStorage gameMediaStorage;

    public GetGameSummaryService(GameRepository games, GameMediaRepository gameMedia, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.gameMedia = gameMedia;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GameSummary> execute(List<UUID> ids) {

        List<GameId> gameIds = ids
                .stream()
                .map(GameId::new)
                .toList();

        return games.findAllById(gameIds)
                .stream()
                .map(game -> {
                    String thumbnailUrl = gameMedia.findByGameIdAndRoleThumbnail(game.getId())
                            .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                            .orElse(null);

                    return new GameSummary(
                            game.getId().value(),
                            game.getTitle().value(),
                            game.getBasePrice().amount(),
                            thumbnailUrl
                    );
                })
                .toList();
    }
}
