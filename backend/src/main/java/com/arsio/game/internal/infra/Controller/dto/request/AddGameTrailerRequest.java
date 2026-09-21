package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddGameTrailerRequest(

        @NotBlank(message = "{externalUrl.require}")
        String externalUrl
) {
}
