package com.arsio.user.internal.domain.valueobject;

import com.arsio.user.internal.domain.exception.InvalidProfileImageKeyException;

public record ObjectKey(String value) {

    public ObjectKey {
        value = value == null ? "" : value.trim();
        validate(value);
    }

    public static void validate(String value) {
        if (value == null || value.isEmpty())
            throw new InvalidProfileImageKeyException();
    }

    @Override
    public String toString() {
        return "ProfileImageKey[PROTECTED]";
    }
}
