package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class ProfileNotFoundException extends NotFoundException {

    public ProfileNotFoundException() {
        super(
                "Profile Not Found.",
                ErrorTypes.USER_PROFILE_NOT_FOUND,
                ErrorCode.USER_PROFILE_NOT_FOUND
        );
    }

    public ProfileNotFoundException(String message) {
        super(
                message,
                ErrorTypes.USER_PROFILE_NOT_FOUND,
                ErrorCode.USER_PROFILE_NOT_FOUND
        );
    }
}
