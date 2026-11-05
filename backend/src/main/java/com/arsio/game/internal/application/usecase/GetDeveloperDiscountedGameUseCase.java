package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service("gameGetDeveloperDiscountedGamesUseCase")
@Transactional
public class GetDeveloperDiscountedGameUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final DiscountRepository discounts;
    private final GameMediaStorage gameMediaStorage;

    public GetDeveloperDiscountedGameUseCase(GameRepository games, GameMediaRepository gameMediaRepository, DiscountRepository discounts, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.discounts = discounts;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GetDiscountedGameView> execute(UUID developerId) {

        List<Game> gameList = games.findAllDiscountedByDeveloperId(developerId);

        return gameList.stream()
                .map(game -> {
                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    Discount discount = discounts.findByGameId(game.getId())
                            .orElseThrow(DiscountNotFoundException::new);

                    BigDecimal decimalPercentage = discount.getPercentage().value().divide(BigDecimal.valueOf(100));
                    Money discountAmount = game.getBasePrice().multiply(decimalPercentage);
                    Money discountedPrice = game.getBasePrice().subtract(discountAmount);

                    return new GetDiscountedGameView(
                            game.getId().value(),
                            game.getTitle().value(),
                            discountedPrice.amount(),
                            discount.getPercentage().value(),
                            thumbnailUrl
                    );
                })
                .toList();
    }
}
