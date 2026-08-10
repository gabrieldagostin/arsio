package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUsernameRequest(
        @NotBlank(message = "{username.required}")
        @Size(min = 6, max = 50, message = "{username.size}")
        String newUsername
) {
}
