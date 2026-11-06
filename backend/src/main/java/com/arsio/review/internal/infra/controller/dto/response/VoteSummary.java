package com.arsio.review.internal.infra.controller.dto.response;

public record VoteSummary(
        Long likes,
        Long dislikes
) {
}
