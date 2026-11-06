package com.arsio.wishlist.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record WishlistId(UUID value) {

    public WishlistId {
        Objects.requireNonNull(value, "WishlistId cannot be null.");
    }

    public static WishlistId generate() {
        return new WishlistId(UUID.randomUUID());
    }
}
