package com.arsio.developer.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DeveloperId(UUID value) {

    public DeveloperId {
        Objects.requireNonNull(value, "DeveloperId cannot be null.");
    }

    public static DeveloperId generate() {
        return new DeveloperId(UUID.randomUUID());
    }
}
