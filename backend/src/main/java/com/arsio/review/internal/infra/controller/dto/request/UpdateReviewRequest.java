package com.arsio.review.internal.infra.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateReviewRequest(

        @NotNull(message = "{rating.require}")
        @Min(value = 1, message = "{rating.min}")
        @Max(value = 5, message = "{rating.max}")
        Integer rating,

        @Size(max = 300, message = "{comment.max}")
        String comment
) {
}
