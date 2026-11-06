package com.arsio.review.internal.application.command;

import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;
import com.arsio.review.internal.domain.valueobject.ReviewId;

public record UpdateReviewCommand(
        ReviewId reviewId,
        Rating rating,
        Comment comment
) {
}
