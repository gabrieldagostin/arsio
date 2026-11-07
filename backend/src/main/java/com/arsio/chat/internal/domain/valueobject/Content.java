package com.arsio.chat.internal.domain.valueobject;

import com.arsio.chat.internal.domain.exception.InvalidContentException;

public record Content(String value) {

    public Content {
        value = value == null ? null : value.trim();
        validate(value);
    }

    public void validate(String value) {

        if (value == null || value.isBlank())
            throw new InvalidContentException();
    }

    @Override
    public String toString() {
        return "Message[PROTECTED]";
    }
}
