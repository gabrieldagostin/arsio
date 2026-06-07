package com.arsio.auth.internal.application.dto;

public record LogoutCommand(
        String refreshToken
) {
}
