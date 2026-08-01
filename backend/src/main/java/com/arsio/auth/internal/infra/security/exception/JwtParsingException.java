package com.arsio.auth.internal.infra.security.exception;

import com.arsio.shared.exception.UnauthorizedException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class JwtParsingException extends UnauthorizedException {

    public JwtParsingException() {
        super(
                "Error while extracting information.",
                ErrorTypes.AUTH_JWT_PARSING_ERROR,
                ErrorCode.AUTH_JWT_PARSING_ERROR
        );
    }

    public JwtParsingException(String message) {
        super(
                message,
                ErrorTypes.AUTH_JWT_PARSING_ERROR,
                ErrorCode.AUTH_JWT_PARSING_ERROR
        );
    }
}
