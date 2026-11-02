package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataDiscountRepository extends JpaRepository<DiscountEntity, UUID> {

    @Query("""
        SELECT d
        FROM DiscountEntity d
        WHERE d.gameId = :gameId
            AND CURRENT_DATE BETWEEN d.startDate AND d.endDate
    """)
    Optional<DiscountEntity> findByGameId(@Param("gameId") UUID gameId);
}
