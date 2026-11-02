package com.arsio.developer.internal.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ApplyGameDiscountCommand(
        UUID gameId,
        BigDecimal percentage,
        LocalDate startDate,
        LocalDate endDate
) {
}
