package com.arsio.user.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record FriendshipId(UUID value) {

    public FriendshipId {
        Objects.requireNonNull(value, "FriendshipId cannot be null.");
    }

    public static FriendshipId generate() {
        return new FriendshipId(UUID.randomUUID());
    }
}
