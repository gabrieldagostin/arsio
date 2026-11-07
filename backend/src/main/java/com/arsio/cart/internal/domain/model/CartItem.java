package com.arsio.cart.internal.domain.model;

import com.arsio.cart.internal.domain.valueobject.CartId;

import java.util.UUID;

public class CartItem {

    private final CartId id;
    private final CartId cartId;
    private final UUID gameId;

    public CartItem(CartId id, CartId cartId, UUID gameId) {
        this.id = id;
        this.cartId = cartId;
        this.gameId = gameId;
    }

    public static CartItem create(UUID gameId, CartId cartId) {
        return new CartItem(
                CartId.generate(),
                cartId,
                gameId
        );
    }

    public CartId getId() {
        return id;
    }

    public CartId getCartId() {
        return cartId;
    }

    public UUID getGameId() {
        return gameId;
    }
}
