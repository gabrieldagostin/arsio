package com.arsio.auth.internal.infra.controller.dto;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        AuthenticatedUserResponse userResponse
) {
}
