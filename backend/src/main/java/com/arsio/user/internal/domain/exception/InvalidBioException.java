package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidBioException extends ValidationException {

    public InvalidBioException() {
        super(
                "Invalid bio",
                ErrorTypes.USER_INVALID_BIO,
                ErrorCode.USER_INVALID_BIO
        );
    }

    public InvalidBioException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_BIO,
                ErrorCode.USER_INVALID_BIO
        );
    }
}
