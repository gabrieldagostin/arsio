package com.arsio.chat.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MessageId(UUID value) {

    public MessageId {
        Objects.requireNonNull(value, "MessageId cannot be null.");
    }

    public static MessageId generate() {
        return new MessageId(UUID.randomUUID());
    }
}
