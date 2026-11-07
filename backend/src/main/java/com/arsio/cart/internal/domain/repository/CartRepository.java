package com.arsio.cart.internal.domain.repository;

import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.valueobject.CartStatus;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {

    Optional<Cart> findByUserIdAndStatus(UUID userId, CartStatus status);

    Cart save(Cart cart);
}
