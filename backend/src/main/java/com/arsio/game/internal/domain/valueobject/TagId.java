package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record TagId(UUID value) {

    public TagId {
        Objects.requireNonNull(value, "TagId cannot be null.");
    }

    public static TagId generate() {
        return new TagId(UUID.randomUUID());
    }
}
