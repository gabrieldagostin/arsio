package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record GenreId(UUID value) {

    public GenreId {
        Objects.requireNonNull(value, "GenreId cannot be null.");
    }

    public static GenreId generate() {
        return new GenreId(UUID.randomUUID());
    }
}
