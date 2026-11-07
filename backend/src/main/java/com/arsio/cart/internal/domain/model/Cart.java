package com.arsio.cart.internal.domain.model;

import com.arsio.cart.internal.domain.valueobject.CartId;
import com.arsio.cart.internal.domain.valueobject.CartStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Cart {

    private final CartId id;
    private final UUID userId;
    private CartStatus status;
    private Set<CartItem> items;

    public Cart(CartId id, UUID userId, CartStatus status, Set<CartItem> items) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.items = items != null ? new HashSet<>(items) : new HashSet<>();
    }

    public static Cart create(UUID userId, CartStatus status) {
        return new Cart(
                CartId.generate(),
                userId,
                status,
                new HashSet<>()
        );
    }

    public CartId getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public CartStatus getStatus() {
        return status;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void addGame(UUID gameId, CartId cartId) {
        boolean alreadyExists = this.items.stream()
                .anyMatch(item -> item.getGameId().equals(gameId));

        if (!alreadyExists) {
            this.items.add(CartItem.create(gameId, cartId));
        }
    }
}
