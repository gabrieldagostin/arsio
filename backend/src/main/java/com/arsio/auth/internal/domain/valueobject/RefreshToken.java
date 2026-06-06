package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidTokenException;

public record RefreshToken(String value) {

    public RefreshToken {
        validate(value);
    }

    private static String validate(String value) {
        if (value == null || value.isBlank()){
            throw new InvalidTokenException("Valor do token não valido");
        }
        return value;
    }

}
