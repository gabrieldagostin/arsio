package com.arsio.review.api.dto;

import java.util.UUID;

public record TopRatedGameView(
        UUID gameId,
        Double averageRating,
        Long totalReviews
) {
}
