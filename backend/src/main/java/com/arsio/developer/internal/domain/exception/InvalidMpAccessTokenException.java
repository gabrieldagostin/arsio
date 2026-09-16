package com.arsio.developer.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidMpAccessTokenException extends ValidationException {

    public InvalidMpAccessTokenException() {
        super(
                "Invalid mp access token.",
                ErrorTypes.DEVELOPER_INVALID_MP_ACCESS_TOKEN,
                ErrorCode.DEVELOPER_INVALID_MP_ACCESS_TOKEN
        );
    }

    public InvalidMpAccessTokenException(String message) {
        super(
                message,
                ErrorTypes.DEVELOPER_INVALID_MP_ACCESS_TOKEN,
                ErrorCode.DEVELOPER_INVALID_MP_ACCESS_TOKEN
        );
    }
}
