package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Discount;
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

@Service("gameGetScheduleDiscountedGamesUseCase")
@Transactional
public class GetScheduleDiscountedGamesUseCase {

    private final DiscountRepository discounts;
    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetScheduleDiscountedGamesUseCase(DiscountRepository discounts, GameRepository games, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.discounts = discounts;
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<GetDiscountedGameView> execute(UUID developerId) {
        return games.findAllScheduleDiscounted(developerId)
                .stream()
                .map(game -> {

                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(game.getId())
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    Discount discount = discounts.findScheduledByGameId(game.getId())
                            .orElseThrow(DiscountNotFoundException::new);

                    BigDecimal decimalPercentage = discount.getPercentage().value().divide(BigDecimal.valueOf(100));
                    Money discountAmount = game.getBasePrice().multiply(decimalPercentage);
                    Money discountedPrice = game.getBasePrice().subtract(discountAmount);

                    return new GetDiscountedGameView(
                            game.getId().value(),
                            game.getTitle().value(),
                            discountedPrice.amount(),
                            discountAmount.amount(),
                            thumbnailUrl
                    );
                })
                .toList();

    }
}
