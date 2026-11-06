package com.arsio.review.internal.application.command;

import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;

import java.util.UUID;

public record CreateReviewCommand(
        UUID gameId,
        UUID userId,
        Rating rating,
        Comment comment
) {
}
