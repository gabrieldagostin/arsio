package com.arsio.wishlist.internal.infra.persistence.repository;

import com.arsio.wishlist.internal.infra.persistence.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataWishlistRepository extends JpaRepository<WishlistEntity, UUID> {
}
