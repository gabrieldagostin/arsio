package com.arsio.auth.internal.infra.controller.dto.response;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        AuthenticatedUserResponse userResponse
) {
}
