package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.DiscountId;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.Percentage;
import com.arsio.shared.money.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Discount {

    private final DiscountId id;
    private final GameId gameId;
    private Percentage percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Discount(DiscountId id, GameId gameId, Percentage percentage, LocalDate startDate, LocalDate endDate, boolean active) {
        this.id = id;
        this.gameId = gameId;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }

    public static Discount create(GameId gameId, BigDecimal percentage, LocalDate startDate, LocalDate endDate) {
        return new Discount(
                DiscountId.generate(),
                gameId,
                new Percentage(percentage),
                startDate,
                endDate,
                true
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

    public boolean isActive() {
        return active;
    }

    public void update(BigDecimal percentage, LocalDate startDate, LocalDate endDate) {
        this.percentage = new Percentage(percentage);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void deactivate() {
        this.active = false;
    }

    public Money calculateAmount(Money basePrice) {

        BigDecimal decimalPercentage = this.percentage.value().divide(BigDecimal.valueOf(100));

        return basePrice.multiply(decimalPercentage);
    }

    public Money applyTo(Money basePrice) {
        return basePrice.subtract(calculateAmount(basePrice));
    }
}
