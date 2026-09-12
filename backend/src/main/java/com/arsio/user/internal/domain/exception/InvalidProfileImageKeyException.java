package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidProfileImageKeyException extends ValidationException {

    public InvalidProfileImageKeyException() {
        super(
                "Invalid profile image key, please try again.",
                ErrorTypes.USER_INVALID_PROFILE_IMAGE_KEY,
                ErrorCode.USER_INVALID_PROFILE_IMAGE_KEY
        );
    }

    public InvalidProfileImageKeyException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_PROFILE_IMAGE_KEY,
                ErrorCode.USER_INVALID_PROFILE_IMAGE_KEY
        );
    }
}
