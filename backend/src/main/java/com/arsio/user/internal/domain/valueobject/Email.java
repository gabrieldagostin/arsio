package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidEmailFormatException;
import com.arsio.user.internal.domain.exception.InvalidEmailLengthException;

import java.util.Locale;

public record Email(String value) {

    public Email {
        value = value == null ? null : value.trim().toLowerCase(Locale.ROOT);
        validate(value);
    }

    private static void validate(String value) {
        if (value == null)
            throw new InvalidEmailFormatException("Email cannot be null.");

        if (value.length() > 320)
            throw new InvalidEmailLengthException();

        if (value.isBlank() || !value.contains("@"))
            throw new InvalidEmailFormatException("Email must contain @.");
    }

    @Override
    public String toString() {
        return "Email[PROTECTED]";
    }
}
