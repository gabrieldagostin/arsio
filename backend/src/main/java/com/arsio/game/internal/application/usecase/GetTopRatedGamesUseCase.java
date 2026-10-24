package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.game.internal.infra.storage.GameMediaMinioFileStorage;
import com.arsio.review.api.facade.ReviewFacade;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetTopRatedGamesUseCase {

    private final ReviewFacade reviewFacade;
    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaMinioFileStorage gameMediaMinioFileStorage;

    public GetTopRatedGamesUseCase(ReviewFacade reviewFacade, GameRepository games, GameMediaRepository gameMediaRepository, GameMediaMinioFileStorage gameMediaMinioFileStorage) {
        this.reviewFacade = reviewFacade;
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaMinioFileStorage = gameMediaMinioFileStorage;
    }

    public List<ListGamesResponse> execute(Pagination pagination) {

        return reviewFacade.findTopRatedGames(pagination)
                .stream()
                .map(topRatedGameView -> {

                    Game game = games.findById(new GameId(topRatedGameView.gameId()))
                            .orElseThrow(GameNotFoundException::new);

                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(new GameId(topRatedGameView.gameId()))
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaMinioFileStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    return new ListGamesResponse(
                            game.getId().value(),
                            game.getTitle().value(),
                            game.getBasePrice().amount(),
                            thumbnailUrl
                    );
                })
                .toList();
    }
}
