package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOperatingSystemRequest(

        @NotBlank(message = "{operatingSystem.require}")
        @Size(max = 50, message = "{operatingSystem.size}")
        String name
) {
}
