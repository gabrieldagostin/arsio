package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record GameMediaId(UUID value) {

    public GameMediaId {
        Objects.requireNonNull(value, "GameMediaId cannot be null.");
    }

    public static GameMediaId generate(){
        return new GameMediaId(UUID.randomUUID());
    }
}
