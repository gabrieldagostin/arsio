package com.arsio.order.internal.infra.persistance.repository;

import com.arsio.order.internal.infra.persistance.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataOrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
