package com.arsio.user.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        Objects.requireNonNull(value, "UserId cannot be null.");
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
