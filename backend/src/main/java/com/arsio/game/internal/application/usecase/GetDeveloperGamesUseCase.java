package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetDeveloperGamesUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetDeveloperGamesUseCase(GameRepository gameRepository, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.games = gameRepository;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GetDeveloperGamesView> execute(UUID developerId) {

        List<Game> gameList = games.findAllByDeveloperId(developerId);

        return gameList.stream()
                .map(game -> {

                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    return new GetDeveloperGamesView(
                            game.getId().value(),
                            game.getTitle().value(),
                            game.getBasePrice().amount(),
                            thumbnailUrl
                    );
                })
                .toList();
    }
}
