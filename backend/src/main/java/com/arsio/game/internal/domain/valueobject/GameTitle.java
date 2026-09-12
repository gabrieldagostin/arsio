package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidGameTitleException;

public record GameTitle(String value) {

    public GameTitle {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private void validate(String value) {

        if (value == null || value.isBlank() || value.length() > 150)
            throw new InvalidGameTitleException();
    }
}
