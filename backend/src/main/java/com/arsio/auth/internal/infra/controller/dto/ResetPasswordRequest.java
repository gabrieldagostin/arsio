package com.arsio.auth.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(

        @NotBlank
        String token,

        @NotBlank
        String newPassword
) {
}
