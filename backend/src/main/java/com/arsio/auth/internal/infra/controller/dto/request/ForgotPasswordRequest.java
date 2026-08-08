package com.arsio.auth.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @NotBlank(message = "{email.require}")
        String email
) {
}
