package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(

        @NotBlank(message = "{passwordHash.require}")
        @Size(min = 8, max = 72, message = "{passwordHash.size}")
        String password,

        @NotBlank(message = "{passwordHash.require}")
        @Size(min = 8, max = 72, message = "{passwordHash.size}")
        String newPassword
) {
}
