package com.arsio.review.internal.infra.persistence.repository;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.internal.infra.persistence.entity.ReviewEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    @Query("""
    SELECT new com.arsio.review.api.facade.TopRatedGameView(
        r.gameId,
        AVG(r.rating),
        COUNT(r.id)
    )
    FROM ReviewEntity r
    GROUP BY r.gameId
    ORDER BY AVG(r.rating) DESC, COUNT(r.id) DESC
    """)
    List<TopRatedGameView> findTopRatedGames(Pageable pageable);
}
