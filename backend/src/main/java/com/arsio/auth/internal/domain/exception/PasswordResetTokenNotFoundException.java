package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class PasswordResetTokenNotFoundException extends NotFoundException {

    public PasswordResetTokenNotFoundException() {
        super(
                "Password reset token not found.",
                ErrorTypes.AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND,
                ErrorCode.AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND
        );
    }

    public PasswordResetTokenNotFoundException(String message) {
        super(
                message,
                ErrorTypes.AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND,
                ErrorCode.AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND
        );
    }
}
