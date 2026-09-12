package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidRequirementException;

public record Storage(Double value) {

    public Storage {
        validate(value);
    }

    private void validate(Double value) {

        if (value == null || value < 0)
            throw new InvalidRequirementException("Invalid storage, please try again.");
    }
}
