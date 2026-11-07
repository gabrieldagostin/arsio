package com.arsio.cart.internal.domain.model;

import java.time.Instant;
import java.util.UUID;

public class CartItem {

    private final UUID gameId;
    private final Instant addedAt;

    public CartItem(UUID gameId, Instant addedAt) {
        this.gameId = gameId;
        this.addedAt = addedAt;
    }

    public static CartItem create(UUID gameId) {
        return new CartItem(
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
