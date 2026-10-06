package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetFeaturedGamesUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetFeaturedGamesUseCase(GameRepository games, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public PageResult<ListGamesResponse> execute(Pagination pagination) {

        PageResult<Game> gameList = games.findAllByFeaturedTrue(pagination);

        List<ListGamesResponse> content = gameList.content()
                .stream()
                .map(game -> {

                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    return new ListGamesResponse(
                            game.getId().value(),
                            game.getTitle().value(),
                            game.getBasePrice().amount(),
                            thumbnailUrl
                    );
                })
                .toList();

        return new PageResult<>(
                content,
                gameList.page(),
                gameList.size(),
                gameList.totalElements(),
                gameList.totalPages()
        );
    }
}
