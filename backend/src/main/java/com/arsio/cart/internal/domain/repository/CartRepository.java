package com.arsio.cart.internal.domain.repository;

import com.arsio.cart.internal.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {

    Optional<Cart> findByUserId(UUID userId);
}
