package com.arsio.user.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ProfileId(UUID value) {

    public ProfileId {
        Objects.requireNonNull(value, "ProfileId cannot be null.");
    }

    public static ProfileId generate() {
        return new ProfileId(UUID.randomUUID());
    }
}
