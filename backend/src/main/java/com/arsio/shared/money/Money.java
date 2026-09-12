package com.arsio.shared.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(
        BigDecimal amount,
        Currency currency
) {

    public Money {
        Objects.requireNonNull(amount, "Amount cannot be null.");
        Objects.requireNonNull(currency, "Currency cannot be null.");

        if (amount.signum() < 0) {
            throw new InvalidMoneyException("Amount cannot be negative.");
        }
    }

    public Money add(Money other) {
        validateCurrency(other);

        return new Money(
                amount.add(other.amount),
                currency
        );
    }

    public Money subtract(Money other) {
        validateCurrency(other);

        return new Money(
                amount.subtract(other.amount),
                currency
        );
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(
                amount.multiply(multiplier),
                currency
        );
    }

    private void validateCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new InvalidMoneyException("Cannot operate with different currencies.");
        }
    }
}
