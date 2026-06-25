package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidTokenException;

public record PasswordToken(String value) {

    public PasswordToken {
        validate(value);
    }

    private String validate(String value) {

        if (value.isBlank() || value == null)
            throw new InvalidTokenException("Password Token inválido");

        return value;
    }
}
