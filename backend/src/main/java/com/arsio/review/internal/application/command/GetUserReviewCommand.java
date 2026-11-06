package com.arsio.review.internal.application.command;

import java.util.UUID;

public record GetUserReviewCommand(
        UUID userId,
        UUID gameId
) {
}
