package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record GameBuildId(UUID value) {

    public GameBuildId {
        Objects.requireNonNull(value, "GameBuildId cannot be null.");
    }

    public static GameBuildId generate() {
        return new GameBuildId(UUID.randomUUID());
    }
}
