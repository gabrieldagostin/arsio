package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidTokenException;

public record AccessToken(String value) {

    public AccessToken {
        validate(value);
    }

    private static String validate(String value) {
        if (value == null || value.isBlank()){
            throw new InvalidTokenException("Valor do token não valido");
        }
        return value;
    }

}
