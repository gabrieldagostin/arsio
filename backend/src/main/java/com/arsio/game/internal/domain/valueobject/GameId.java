package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record GameId(UUID value) {

    public GameId {
        Objects.requireNonNull(value, "GameId cannot be null.");
    }

    public static GameId generate() {
        return new GameId(UUID.randomUUID());
    }
}
