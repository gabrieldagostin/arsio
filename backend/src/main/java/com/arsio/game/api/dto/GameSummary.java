package com.arsio.game.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record GameSummary(
        UUID id,
        String title,
        BigDecimal price,
        BigDecimal basePrice,
        String thumbnailUrl,
        BigDecimal discountedPercentage,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
