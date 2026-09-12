package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RequirementId(UUID value) {

    public RequirementId {
        Objects.requireNonNull(value, "RequirementId cannot be null.");
    }

    public static RequirementId generate() {
        return new RequirementId(UUID.randomUUID());
    }
}
