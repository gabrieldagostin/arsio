package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidCountryException extends ValidationException {

    public InvalidCountryException() {
        super(
                "Invalid country code, please try again.",
                ErrorTypes.USER_INVALID_PROFILE_COUNTRY_CODE,
                ErrorCode.USER_INVALID_PROFILE_COUNTRY_CODE
        );
    }

    public InvalidCountryException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_PROFILE_COUNTRY_CODE,
                ErrorCode.USER_INVALID_PROFILE_COUNTRY_CODE
        );
    }
}
