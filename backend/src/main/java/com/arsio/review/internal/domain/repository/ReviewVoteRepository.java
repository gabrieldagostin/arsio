package com.arsio.review.internal.domain.repository;

import com.arsio.review.internal.domain.model.ReviewVote;
import com.arsio.review.internal.domain.valueobject.ReviewId;

import java.util.Optional;

public interface ReviewVoteRepository {

    Optional<ReviewVote> findById(ReviewId reviewId);

    void save(ReviewVote reviewVote);
}
