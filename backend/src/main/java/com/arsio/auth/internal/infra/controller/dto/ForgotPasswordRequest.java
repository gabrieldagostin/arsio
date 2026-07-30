package com.arsio.auth.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @NotBlank(message = "{email.require}")
        String email
) {
}
