package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateOperatingSystemRequest(

        @NotBlank(message = "{name.require}")
        @Size(max = 50, message = "{name.size}")
        String name
) {
}
