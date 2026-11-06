package com.arsio.review.internal.application.command;

import com.arsio.review.internal.domain.valueobject.ReviewId;

import java.util.UUID;

public record LikeReviewCommand(
        ReviewId reviewId,
        UUID userId
) {
}
