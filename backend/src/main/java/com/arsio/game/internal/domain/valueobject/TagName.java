package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidTagNameException;

public record TagName(String value) {

    public TagName {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private void validate(String value) {

        if (value == null || value.isBlank() || value.length() > 100)
            throw new InvalidTagNameException();
    }
}
