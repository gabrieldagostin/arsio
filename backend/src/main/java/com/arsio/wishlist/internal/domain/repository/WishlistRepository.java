package com.arsio.wishlist.internal.domain.repository;

import com.arsio.wishlist.internal.domain.model.Wishlist;

import java.util.Optional;
import java.util.UUID;

public interface WishlistRepository {

    Optional<Wishlist> findByUserId(UUID userId);

    Wishlist save(Wishlist wishlist);
}
