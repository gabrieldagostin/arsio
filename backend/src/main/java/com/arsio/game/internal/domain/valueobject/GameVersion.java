package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidGameVersionException;

public record GameVersion(String value) {

    public GameVersion {
        validate(value);
    }

    private void validate(String value) {

        if (value == null || value.isBlank())
            throw new InvalidGameVersionException();
    }
}
