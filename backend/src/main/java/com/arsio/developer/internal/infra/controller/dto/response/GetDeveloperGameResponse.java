package com.arsio.developer.internal.infra.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record GetDeveloperGameResponse(
        UUID gameId,
        String title,
        BigDecimal basePrice,
        String thumbnailUrl
) {
}
