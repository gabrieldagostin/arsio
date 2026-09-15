package com.arsio.shared.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount) {

    public Money {
        Objects.requireNonNull(amount, "Amount cannot be null.");

        if (amount.signum() < 0) {
            throw new InvalidMoneyException("Amount cannot be negative.");
        }
    }

    public Money add(Money other) {

        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other) {

        BigDecimal result = amount.subtract(other.amount);

        if (result.signum() < 0) {
            throw new InvalidMoneyException(
                    "Resulting amount cannot be negative."
            );
        }

        return new Money(result);
    }

    public Money multiply(BigDecimal multiplier) {
        Objects.requireNonNull(multiplier, "Multiplier cannot be null.");

        if (multiplier.signum() < 0) {
            throw new InvalidMoneyException(
                    "Multiplier cannot be negative."
            );
        }

        return new Money(
                amount.multiply(multiplier));
    }
}
