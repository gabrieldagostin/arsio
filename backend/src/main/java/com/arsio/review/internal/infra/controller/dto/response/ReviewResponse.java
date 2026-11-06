package com.arsio.review.internal.infra.controller.dto.response;

import com.arsio.review.internal.domain.dto.AuthorSummary;
import com.arsio.review.internal.domain.dto.VoteSummary;

import java.util.UUID;

public record ReviewResponse(
        UUID id,
        Integer rating,
        String comment,
        VoteSummary voteSummary,
        AuthorSummary authorSummary
) {
}
