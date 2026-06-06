package com.arsio.auth.internal.infra.controller.dto;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
