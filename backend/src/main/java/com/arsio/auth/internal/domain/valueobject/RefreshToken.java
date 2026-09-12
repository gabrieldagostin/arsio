package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidTokenException;

public record RefreshToken(String value) {

    public RefreshToken {
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank())
            throw new InvalidTokenException("Invalid refresh token, please try again.");
    }

    @Override
    public String toString() {
        return "RefreshToken[PROTECTED]";
    }
}
