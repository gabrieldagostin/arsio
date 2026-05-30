package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidUsernameException;
import com.arsio.auth.internal.domain.exception.InvalidUsernameLengthException;

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

    public String getUsername() {
        return value;
    }

    public String getNormalized() {
        return value.toLowerCase();
    }
}
