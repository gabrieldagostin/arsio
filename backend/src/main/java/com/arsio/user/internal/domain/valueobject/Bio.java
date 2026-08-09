package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidBioException;

public record Bio(String value) {

    public Bio {
        value = value == null ? null : value.trim();
        validate();
    }

    public void validate() {
        if (value == null || value.isBlank())
            throw new InvalidBioException();
        if (value.length() > 300)
            throw new InvalidBioException();
    }
}
