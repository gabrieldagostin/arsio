package com.arsio.auth.internal.application.command;

import com.arsio.auth.internal.domain.valueobject.RefreshToken;

public record RefreshTokenCommand(
        RefreshToken refreshToken
) {
}
