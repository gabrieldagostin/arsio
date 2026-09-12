package com.arsio.auth.internal.infra.security.exception;

import com.arsio.shared.exception.UnauthorizedException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class JwtGenerationException extends UnauthorizedException {

    public JwtGenerationException() {
        super(
                "Error while generating token, please try again.",
                ErrorTypes.AUTH_JWT_GENERATION_ERROR,
                ErrorCode.AUTH_JWT_GENERATION_ERROR
        );
    }

    public JwtGenerationException(String message) {
        super(
                message,
                ErrorTypes.AUTH_JWT_GENERATION_ERROR,
                ErrorCode.AUTH_JWT_GENERATION_ERROR
        );
    }
}
