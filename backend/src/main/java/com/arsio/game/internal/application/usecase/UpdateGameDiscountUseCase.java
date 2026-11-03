package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.dto.UpdateGameDiscountView;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.DiscountRepository;
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
public class UpdateGameDiscountUseCase {

    private GameRepository games;
    private DiscountRepository discounts;

    public UpdateGameDiscountUseCase(GameRepository games, DiscountRepository discounts) {
        this.games = games;
        this.discounts = discounts;
    }

    public UpdateGameDiscountView execute(UUID value, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {

        GameId gameId = new GameId(value);

        Discount discount = discounts.findByGameId(gameId)
                .orElseThrow(DiscountNotFoundException::new);

        discount.update(
                percentage,
                startDate,
                endDate
        );

        discounts.save(discount);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        BigDecimal decimalPercentage = discount.getPercentage().value().divide(BigDecimal.valueOf(100));
        Money discountAmount = game.getBasePrice().multiply(decimalPercentage);
        Money discountedPrice = game.getBasePrice().subtract(discountAmount);

        return new UpdateGameDiscountView(
                discountedPrice.amount(),
                discount.getPercentage().value(),
                discount.getStartDate(),
                discount.getEndDate()
        );
    }
}
