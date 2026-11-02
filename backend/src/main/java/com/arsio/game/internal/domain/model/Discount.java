package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.DiscountId;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.Percentage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Discount {

    private final DiscountId id;
    private final GameId gameId;
    private Percentage percentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(DiscountId id, GameId gameId, Percentage percentage, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.gameId = gameId;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Discount create(GameId gameId, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {
        return new Discount(
                DiscountId.generate(),
                gameId,
                new Percentage(percentage),
                startDate,
                endDate
        );
    }

    public DiscountId getId() {
        return id;
    }

    public GameId getGameId() {
        return gameId;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
