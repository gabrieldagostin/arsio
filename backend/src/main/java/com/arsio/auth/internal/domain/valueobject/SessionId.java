package com.arsio.auth.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SessionId(UUID value) {

    public SessionId {
        Objects.requireNonNull(value, "SessionId cannot be null.");
    }

    public static SessionId generate() {
        return new SessionId(UUID.randomUUID());
    }
}
