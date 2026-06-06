package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidPasswordException;

public record Password(String hashedValue) {

    public Password {
        validate(hashedValue);
    }

    private String validate(String hashedValue) {
        if (hashedValue.isBlank() || hashedValue == null) {
            throw new InvalidPasswordException("Senha Inválida");
        }
        return hashedValue;
    }
}
