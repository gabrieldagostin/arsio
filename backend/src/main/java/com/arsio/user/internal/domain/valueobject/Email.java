package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidEmailException;

import java.util.Locale;

public record Email(String value) {

    public Email {
        value = value == null ? null : value.trim().toLowerCase(Locale.ROOT);
        validate(value);
    }

    private static void validate(String value) {
        if (value == null)
            throw new InvalidEmailException();

        if (value.isBlank() || !value.contains("@"))
            throw new InvalidEmailException();

        if (value.length() > 320)
            throw new InvalidEmailException("Email must be less than 320 characters");
    }

    @Override
    public String toString() {
        return "Email[PROTECTED]";
    }
}
