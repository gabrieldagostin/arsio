package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidUsernameException;
import com.arsio.user.internal.domain.exception.InvalidUsernameLengthException;

public record Username(String value) {

    public Username {
        validate(value);
    }

    private String validate(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidUsernameException();
        }

        if (value.length() < 6 || value.length() > 50) {
            throw new InvalidUsernameLengthException();
        }

        return value.trim();
    }

    public String getNormalized() {
        return value.toLowerCase();
    }
}
