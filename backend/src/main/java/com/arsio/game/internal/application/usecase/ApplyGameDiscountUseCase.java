package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.GetDiscountedGameView;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.shared.money.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class ApplyGameDiscountUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final DiscountRepository discounts;
    private final GameMediaStorage gameMediaStorage;

    public ApplyGameDiscountUseCase(GameRepository games, GameMediaRepository gameMediaRepository, DiscountRepository discounts, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.discounts = discounts;
        this.gameMediaStorage = gameMediaStorage;
    }

    public GetDiscountedGameView execute(UUID value, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {

        GameId gameId = new GameId(value);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        Discount discount = Discount.create(
                gameId,
                percentage,
                startDate,
                endDate
        );

        discounts.save(discount);

        String thumbnailUrl = gameMediaRepository.findByGameIdAndRoleThumbnail(gameId)
                .map(media -> gameMediaStorage.generatePresignedDownloadUrl(media.getObjectKey().value()))
                .orElse(null);

        Money discountedAmount = discount.calculateAmount(game.getBasePrice());
        Money price = discount.applyTo(game.getBasePrice());

        return new GetDiscountedGameView(
                game.getId().value(),
                game.getTitle().value(),
                price.amount(),
                game.getBasePrice().amount(),
                thumbnailUrl,
                discount.getPercentage().value(),
                discountedAmount.amount(),
                discount.getStartDate(),
                discount.getEndDate()
        );
    }
}
