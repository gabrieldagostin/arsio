package com.arsio.auth.internal.application.dto;

public record ResetPasswordCommand(
        String token,
        String newPassword
) {
}
