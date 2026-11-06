package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.DiscountInfo;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.review.api.facade.ReviewFacade;
import com.arsio.shared.money.Money;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetTopRatedGamesUseCase {

    private final ReviewFacade reviewFacade;
    private final GameRepository games;
    private final DiscountRepository discounts;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetTopRatedGamesUseCase(ReviewFacade reviewFacade, GameRepository games, DiscountRepository discounts, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.reviewFacade = reviewFacade;
        this.games = games;
        this.discounts = discounts;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<ListGamesResponse> execute(Pagination pagination) {

        return reviewFacade.findTopRatedGames(pagination)
                .stream()
                .map(topRatedGameView -> {

                    Game game = games.findById(new GameId(topRatedGameView.gameId()))
                            .orElseThrow(GameNotFoundException::new);

                    String thumbnailUrl = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                            .orElseThrow(GameMediaNotFoundException::new);

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

                    return new ListGamesResponse(
                            game.getId().value(),
                            game.getTitle().value(),
                            price.amount(),
                            game.getBasePrice().amount(),
                            thumbnailUrl,
                            discountInfo
                    );
                })
                .toList();
    }
}
