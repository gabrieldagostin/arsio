package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidGameFileSizeException;

public record FileSize(Integer value) {

    public FileSize {
        validate(value);
    }

    private void validate(Integer value) {

        if (value == null || value <= 0)
            throw new InvalidGameFileSizeException();
    }
}
