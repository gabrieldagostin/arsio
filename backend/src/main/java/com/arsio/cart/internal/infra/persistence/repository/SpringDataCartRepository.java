package com.arsio.cart.internal.infra.persistence.repository;

import com.arsio.cart.internal.infra.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCartRepository extends JpaRepository<CartEntity, UUID> {
}
