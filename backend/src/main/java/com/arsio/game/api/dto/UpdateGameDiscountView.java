package com.arsio.game.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateGameDiscountView(
        BigDecimal discountedPrice,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
