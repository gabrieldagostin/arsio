package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidUsernameException;

import java.util.Locale;

public record Username(String value) {

    public Username {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank())
            throw new InvalidUsernameException();

        if (value.length() < 6 || value.length() > 50)
            throw new InvalidUsernameException("Username must be between 6 and 20 characters long.");
    }

    public String getNormalized() {
        return value.toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return "Username[PROTECTED]";
    }
}
