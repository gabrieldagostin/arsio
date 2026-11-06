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

    public static Wishlist createForUser(UUID userId) {
        return new Wishlist(
                new WishlistId(WishlistId.generate().value()),
                userId,
                new ArrayList<>()
        );
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

    public void addGame(UUID gameId) {
        Objects.requireNonNull(gameId, "gameId cannot be null.");
        if (!gameIds.contains(gameId)) {
            this.gameIds.add(gameId);
        }
    }

    public void removeGame(UUID gameId) {
        this.gameIds.remove(gameId);
    }

    public void removeAll() {
        this.gameIds.clear();
    }
}
