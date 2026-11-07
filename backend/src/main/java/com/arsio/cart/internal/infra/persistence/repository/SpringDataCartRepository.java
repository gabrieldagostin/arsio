package com.arsio.cart.internal.infra.persistence.repository;

import com.arsio.cart.internal.domain.valueobject.CartStatus;
import com.arsio.cart.internal.infra.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCartRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findByUserIdAndStatus(UUID userId, CartStatus status);
}
