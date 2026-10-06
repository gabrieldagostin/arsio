package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidRequirementException;

import java.math.BigDecimal;

public record Storage(BigDecimal value) {

    public Storage {
        validate(value);
    }

    private void validate(BigDecimal value) {

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidRequirementException("Invalid storage, please try again.");
        }
    }
}
