package com.arsio.review.internal.domain.model;

import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.domain.valueobject.VoteType;

import java.util.UUID;

public class ReviewVote {

    private final ReviewId id;
    private final ReviewId reviewId;
    private final UUID userId;
    private VoteType voteType;

    public ReviewVote(ReviewId id, ReviewId reviewId, UUID userId, VoteType voteType) {
        this.id = id;
        this.reviewId = reviewId;
        this.userId = userId;
        this.voteType = voteType;
    }

    public static ReviewVote create(ReviewId reviewId, UUID userId, VoteType voteType) {
        return new ReviewVote(
                ReviewId.generate(),
                reviewId,
                userId,
                voteType
        );
    }

    public ReviewId getId() {
        return id;
    }

    public ReviewId getReviewId() {
        return reviewId;
    }

    public UUID getUserId() {
        return userId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void like() {
        this.voteType = VoteType.LIKE;
    }
}
