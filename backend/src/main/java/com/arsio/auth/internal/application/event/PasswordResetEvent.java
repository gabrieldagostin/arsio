package com.arsio.auth.internal.application.event;

public record PasswordResetEvent(
        String email,
        String resetLink
) {
}
