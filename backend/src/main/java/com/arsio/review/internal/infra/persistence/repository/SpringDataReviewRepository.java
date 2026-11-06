package com.arsio.review.internal.infra.persistence.repository;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.internal.infra.persistence.entity.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    @Query("""
        SELECT new com.arsio.review.api.dto.TopRatedGameView(
            r.gameId,
            AVG(r.rating) AS avgRating,
            COUNT(r) AS totalReviews
        )
        FROM ReviewEntity r
        GROUP BY r.gameId, r.id
        ORDER BY avgRating DESC, totalReviews DESC
    """)
    List<TopRatedGameView> findTopRatedGames(Pageable pageable);

    @Query("""
        SELECT r
        FROM ReviewEntity r
        LEFT JOIN ReviewVoteEntity v ON v.reviewId = r.id AND v.voteType = 'LIKE'
        WHERE r.gameId = :gameId AND r.active = true
        GROUP BY r.id
        ORDER BY COUNT(v) DESC
    """)
    List<ReviewEntity> findAllByGameIdOrderByLikesCountDesc(UUID gameId);

    Optional<ReviewEntity> findByIdAndActiveTrue(UUID reviewId);
}
