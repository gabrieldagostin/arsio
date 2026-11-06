package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.service.GameMediaService;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.*;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import com.arsio.game.internal.infra.Controller.dto.response.*;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetGameUseCase {

    private final GameRepository games;
    private final DiscountRepository discounts;
    private final GameMediaService gameMediaService;
    private final GenreRepository genres;
    private final TagRepository tags;
    private final GameRequirementRepository gameRequirements;

    public GetGameUseCase(GameRepository games, DiscountRepository discounts, GameMediaService gameMediaService, GenreRepository genres, TagRepository tags, GameRequirementRepository gameRequirements) {
        this.games = games;
        this.discounts = discounts;
        this.gameMediaService = gameMediaService;
        this.genres = genres;
        this.tags = tags;
        this.gameRequirements = gameRequirements;
    }

    public GetGameResponse execute(UUID value) {

        GameId gameId = new GameId(value);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        DiscountInfo discountInfo = discounts.findByGameId(game.getId())
                .map(discount -> new DiscountInfo(
                        discount.getPercentage().value(),
                        discount.calculateAmount(game.getBasePrice()).amount(),
                        discount.getStartDate(),
                        discount.getEndDate()
                ))
                .orElse(null);

        Money price = discounts.findByGameId(game.getId())
                .map(discount -> discount.applyTo(game.getBasePrice()))
                .orElse(game.getBasePrice());

        GetGameMediaResponse gameMediaResponse = gameMediaService.getByGameId(gameId);

        Set<GetGenreResponse> genreResponses = genres.findAllGenresByGameId(gameId)
                .stream()
                .map(genre -> new GetGenreResponse(
                        genre.getId().value(),
                        genre.getName().value()
                ))
                .collect(Collectors.toSet());

        Set<GetTagResponse> tagResponses = tags.findAllTagsByGameId(gameId)
                .stream()
                .map(tag -> new GetTagResponse(
                        tag.getId().value(),
                        tag.getName().value()
                ))
                .collect(Collectors.toSet());

        GetGameRequirementResponse minimumGameRequirement = gameRequirements.findByGameIdAndCategory(gameId, RequirementCategory.MINIMUM);
        GetGameRequirementResponse recommendedGameRequirement = gameRequirements.findByGameIdAndCategory(gameId, RequirementCategory.RECOMMENDED);

        GetGameRequirementsResponse gameRequirementsResponse = new GetGameRequirementsResponse(
                minimumGameRequirement,
                recommendedGameRequirement
        );

        return new GetGameResponse(
                game.getId().value(),
                game.getDeveloperId(),
                game.getTitle().value(),
                game.getDescription().value(),
                price.amount(),
                game.getBasePrice().amount(),
                discountInfo,
                game.getStatus().name(),
                game.getReleaseDate(),
                gameMediaResponse,
                genreResponses,
                tagResponses,
                gameRequirementsResponse
        );
    }
}
