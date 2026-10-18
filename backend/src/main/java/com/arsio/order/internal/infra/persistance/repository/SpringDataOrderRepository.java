package com.arsio.order.internal.infra.persistance.repository;

import com.arsio.order.internal.infra.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, UUID> {
}
