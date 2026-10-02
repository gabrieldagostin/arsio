package com.arsio.game.internal.infra.Controller.dto.response;

import java.time.LocalDate;

public record UpdateGameResponse(
        String title,
        String description,
        LocalDate releaseDate
) {
}
