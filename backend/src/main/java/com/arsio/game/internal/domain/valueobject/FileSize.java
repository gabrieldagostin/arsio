package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidGameFileSizeException;

public record FileSize(Long value) {

    public FileSize {
        validate(value);
    }

    private void validate(Long value) {

        if (value == null || value <= 0)
            throw new InvalidGameFileSizeException();
    }
}
