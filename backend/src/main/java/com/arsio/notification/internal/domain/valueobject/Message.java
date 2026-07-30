package com.arsio.notification.internal.domain.valueobject;

import com.arsio.notification.internal.domain.exception.InvalidMessageException;

public record Message(String value) {

    public Message {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank())
            throw new InvalidMessageException();
    }

    @Override
    public String toString() {
        return "Message[PROTECTED]";
    }
}
