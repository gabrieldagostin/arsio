package com.arsio.review.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateReviewRequest(

        @NotNull(message = "{rating.require}")
        Integer rating,

        String comment
) {
}
