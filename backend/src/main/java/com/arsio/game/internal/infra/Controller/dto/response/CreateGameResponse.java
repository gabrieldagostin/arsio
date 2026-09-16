package com.arsio.game.internal.infra.Controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateGameResponse(
        UUID id,
        UUID developerId,
        String title,
        String description,
        BigDecimal basePrice,
        String status,
        LocalDate releaseDate
) {
}
