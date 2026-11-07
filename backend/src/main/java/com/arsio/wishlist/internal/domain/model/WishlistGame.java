package com.arsio.wishlist.internal.domain.model;

import java.time.Instant;
import java.util.UUID;

public class WishlistGame {

    private final UUID gameId;
    private final Instant addedAt;

    public WishlistGame(UUID gameId, Instant addedAt) {
        this.gameId = gameId;
        this.addedAt = addedAt;
    }

    public static WishlistGame create(UUID gameId) {
        return new WishlistGame(
                gameId,
                Instant.now()
        );
    }

    public UUID getGameId() {
        return gameId;
    }

    public Instant getAddedAt() {
        return addedAt;
    }
}
