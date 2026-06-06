package com.arsio.auth.internal.domain.valueobject;

import com.arsio.auth.internal.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {
        validate(value);
    }

    private String validate(String value) {
        if (value == null || value.isBlank() || !value.contains("@")) {
            throw new InvalidEmailException();
        }
        return value.trim().toLowerCase();
    }

}
