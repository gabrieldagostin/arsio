package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.domain.model.GameStatus;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SpringDataGameRepository extends JpaRepository<GameEntity, UUID> {

    Page<GameEntity> findAllByStatus(GameStatus status, Pageable pageable);

    Page<GameEntity> findByTitleContainingIgnoreCaseAndStatus(String search, GameStatus status, Pageable pageable);

    Page<GameEntity> findAllByFeaturedTrue(Pageable pageable);

    @Query("""
        SELECT g
        FROM GameEntity g
        WHERE g.status = :status
        AND g.releaseDate BETWEEN :startDate AND :endDate
        ORDER BY g.releaseDate DESC
    """)
    Page<GameEntity> findRecentlyReleased(
            @Param("status") GameStatus status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

    @Query("""
        SELECT g
        FROM GameEntity g
        WHERE EXISTS (
            SELECT 1
            FROM DiscountEntity d
            WHERE d.game.id = g.id
                AND d.startDate <= CURRENT_TIMESTAMP
                AND d.endDate >= CURRENT_TIMESTAMP
                AND d.active = TRUE
        )
    """)
    Page<GameEntity> findDiscountedGames(Pageable pageable);

    List<GameEntity> findAllByDeveloperId(UUID developerId);

    @Query("""
        SELECT g
            FROM GameEntity g
            WHERE g.developerId = :developerId
                AND g.id IN (
                SELECT d.game.id
                FROM DiscountEntity d
                WHERE CURRENT_DATE BETWEEN d.startDate AND d.endDate
                    AND d.active = TRUE
            )
    """)
    List<GameEntity> findAllDiscountedByDeveloperId(@Param("developerId") UUID developerId);

    @Query("""
        SELECT g
            FROM GameEntity g
            WHERE g.developerId = :developerId
                AND g.id IN (
                SELECT d.game.id
                FROM DiscountEntity d
                WHERE d.startDate > CURRENT_DATE
                    AND d.active = TRUE
            )
    """)
    List<GameEntity> findAllScheduledDiscounted(@Param("developerId") UUID developerId);
}
