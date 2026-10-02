package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateGameRequest(

        @NotBlank(message = "{title.require}")
        @Size(max = 150, message = "{title.size}")
        String title,

        @NotBlank(message = "{description.require}")
        @Size(max = 1000, message = "{description.size}")
        String description,

        @NotNull(message = "{releaseDate.require}")
        @FutureOrPresent(message = "{releaseDate.futureOrPresent}")
        LocalDate releaseDate
) {
}
