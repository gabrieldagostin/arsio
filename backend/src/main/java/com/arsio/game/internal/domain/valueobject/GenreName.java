package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidGenreNameException;

public record GenreName(String value) {

    public GenreName {
        value = value == null ? null : value.trim();
        validate(value);
    }

    public void validate(String value) {

        if (value == null || value.isBlank() || value.length() > 100)
            throw new InvalidGenreNameException();
    }
}
