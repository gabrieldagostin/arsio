package com.arsio.order.internal.infra.persistance.repository;

import com.arsio.order.api.dto.GameSalesView;
import com.arsio.order.internal.domain.valueobject.OrderStatus;
import com.arsio.order.internal.infra.persistance.entity.OrderEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("""
        SELECT new com.arsio.order.api.dto.GameSalesView(
            oi.gameId,
            COUNT(oi) AS total
        )
        FROM OrderItemEntity oi
        JOIN oi.order o
        WHERE o.status = :status
        GROUP BY oi.gameId
        ORDER BY total DESC
    """)
    List<GameSalesView> findBestSellingGames(
            @Param("status") OrderStatus status,
            Pageable pageable
    );
}
