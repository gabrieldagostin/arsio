package com.arsio.wishlist.internal.domain.model;

import com.arsio.wishlist.internal.domain.valueobject.WishlistId;

import java.util.*;

public class Wishlist {

    private final WishlistId id;
    private final UUID userId;
    private Set<WishlistGame> games;

    public Wishlist(WishlistId id, UUID userId, Set<WishlistGame> games) {
        this.id = id;
        this.userId = userId;
        this.games = games != null ? new HashSet<>(games) : new HashSet<>();
    }

    public static Wishlist createForUser(UUID userId) {
        return new Wishlist(
                WishlistId.generate(),
                userId,
                new HashSet<>()
        );
    }

    public WishlistId getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public Set<WishlistGame> getGames() {
        return games;
    }

    public void addGame(UUID gameId) {
        boolean alreadyExists = this.games.stream()
                .anyMatch(item -> item.getGameId().equals(gameId));

        if (!alreadyExists) {
            this.games.add(WishlistGame.create(gameId));
        }
    }

    public void removeGame(UUID gameId) {
        this.games.removeIf(item -> item.getGameId().equals(gameId));
    }

    public void removeAll() {
        if (!this.games.isEmpty()) {
            this.games.clear();
        }
    }
}
