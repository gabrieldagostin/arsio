package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidDescriptionException;

public record Description(String value) {

    public Description {
        value = value == null ? null : value.trim();
        if (!(value == null)) {
            validate(value);
        }
    }

    public void validate(String value) {

        if (value.length() > 1000)
            throw new InvalidDescriptionException();
    }
}
