package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidPercentageException;

public record Percentage(Double value) {

    public Percentage {
        validate(value);
    }

    private void validate(Double value) {

        if (value < 0 || value > 100)
            throw new InvalidPercentageException();
    }
}
