package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidTokenException;

public record PasswordToken(String value) {

    public PasswordToken {
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank())
            throw new InvalidTokenException("Invalid password token, please try again.");
    }

    @Override
    public String toString() {
        return "PasswordToken[PROTECTED]";
    }
}
