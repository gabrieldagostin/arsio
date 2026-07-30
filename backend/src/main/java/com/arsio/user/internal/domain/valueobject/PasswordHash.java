package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidPasswordLengthException;

public record PasswordHash(String hashedValue) {

    public PasswordHash {
        validate(hashedValue);
    }

    private static void validate(String hashedValue) {
        if (hashedValue == null || hashedValue.isBlank())
            throw new InvalidPasswordLengthException();
    }

    @Override
    public String toString() {
        return "PasswordHash[PROTECTED]";
    }
}
