package com.arsio.review.internal.domain.repository;

import com.arsio.review.api.dto.TopRatedGameView;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.infra.controller.dto.response.VoteSummary;
import com.arsio.shared.pagination.Pagination;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository {

    List<TopRatedGameView> findTopRatedGames(Pagination pagination);

    void save(Review review);

    List<Review> findAllByGameId(UUID gameId);

    Optional<Review> findById(ReviewId reviewId);

    VoteSummary findVoteSummary(ReviewId reviewId);
}
