package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.service.GameMediaService;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.*;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetGameUseCase {

    private final GameRepository games;
    private final GameMediaService gameMediaService;
    private final GenreRepository genres;
    private final TagRepository tags;
    private final GameRequirementRepository gameRequirements;

    public GetGameUseCase(GameRepository games, GameMediaService gameMediaService, GenreRepository genres, TagRepository tags, GameRequirementRepository gameRequirements) {
        this.games = games;
        this.gameMediaService = gameMediaService;
        this.genres = genres;
        this.tags = tags;
        this.gameRequirements = gameRequirements;
    }

    public GetGameResponse execute(UUID value) {

        GameId gameId = new GameId(value);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        GetGameMediaResponse gameMediaResponse = gameMediaService.getByGameId(gameId);

        Set<GetGenreResponse> genreResponses = genres.findAllByGameId(gameId)
                .stream()
                .map(genre -> {
                    return new GetGenreResponse(
                            genre.getId().value(),
                            genre.getName().value()
                    );
                })
                .collect(Collectors.toSet());

        Set<GetTagResponse> tagResponses = tags.findAllByGameId(gameId)
                .stream()
                .map(tag -> {
                    return new GetTagResponse(
                            tag.getId().value(),
                            tag.getName().value()
                    );
                })
                .collect(Collectors.toSet());

        GetGameRequirementResponse minimumGameRequirement = gameRequirements.findByGameIdAndCategoryMinimum(gameId);
        GetGameRequirementResponse recommendedGameRequirement = gameRequirements.findByGameIdAndCategoryRecommended(gameId);

        GetGameRequirementsResponse gameRequirementsResponse = new GetGameRequirementsResponse(
                minimumGameRequirement,
                recommendedGameRequirement
        );

        return new GetGameResponse(
                game.getId().value(),
                game.getDeveloperId(),
                game.getTitle().value(),
                game.getDescription().value(),
                game.getBasePrice().amount(),
                game.getStatus().name(),
                game.getReleaseDate(),
                gameMediaResponse,
                genreResponses,
                tagResponses,
                gameRequirementsResponse
        );
    }
}
