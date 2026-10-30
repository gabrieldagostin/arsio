package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidPercentageException;

import java.math.BigDecimal;

public record Percentage(BigDecimal value) {

    public Percentage {
        validate(value);
    }

    private void validate(BigDecimal value) {

        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal("100")) > 0)
            throw new InvalidPercentageException();
    }
}
