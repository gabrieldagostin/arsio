package com.arsio.wishlist.internal.domain.model;

import com.arsio.wishlist.internal.domain.valueobject.WishlistId;

import java.util.*;

public class Wishlist {

    private final WishlistId id;
    private final UUID userId;
    private final List<UUID> gameIds;

    public Wishlist(WishlistId id, UUID userId, List<UUID> gameIds) {
        this.id = id;
        this.userId = userId;
        this.gameIds = gameIds;
    }

    public WishlistId getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<UUID> getGameIds() {
        return Collections.unmodifiableList(gameIds);
    }
}
