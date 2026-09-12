package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidRequirementException;

public record Ram(Integer value) {

    public Ram {
        validate(value);
    }

    private void validate(Integer value) {

        if (value == null || value < 0)
            throw new InvalidRequirementException("Invalid ram, please try again.");
    }
}
