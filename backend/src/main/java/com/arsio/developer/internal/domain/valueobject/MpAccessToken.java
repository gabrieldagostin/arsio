package com.arsio.developer.internal.domain.valueobject;

import com.arsio.developer.internal.domain.exception.InvalidMpAccessTokenException;

public record MpAccessToken(String value) {

    public MpAccessToken {
        validate(value);
    }

    public void validate(String value) {

        if (value == null || value.isBlank())
            throw new InvalidMpAccessTokenException();
    }

    @Override
    public String toString() {
        return "MpAccessToken[PROTECTED]";
    }
}
