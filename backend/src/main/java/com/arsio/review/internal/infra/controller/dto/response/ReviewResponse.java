package com.arsio.review.internal.infra.controller.dto.response;

import java.util.UUID;

public record ReviewResponse(
        UUID id,
        Integer rating,
        String comment,
        VoteSummary voteSummary,
        AuthorSummary authorSummary
) {
}
