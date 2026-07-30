package com.arsio.notification.internal.domain.valueobject;

import com.arsio.notification.internal.domain.exception.InvalidTitleException;

public record Title(String value) {

    public Title {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank())
            throw new InvalidTitleException();
    }

    @Override
    public String toString() {
        return "Title[PROTECTED]";
    }
}
