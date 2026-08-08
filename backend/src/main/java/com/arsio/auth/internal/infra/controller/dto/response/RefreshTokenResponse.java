package com.arsio.auth.internal.infra.controller.dto.response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
