package com.arsio.notification.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidTitleException extends ValidationException {

    public InvalidTitleException() {
        super(
                "Invalid title, please try again.",
                ErrorTypes.NOTIFICATION_INVALID_TITLE,
                ErrorCode.NOTIFICATION_INVALID_TITLE
        );
    }

    public InvalidTitleException(String message) {
        super(
                message,
                ErrorTypes.NOTIFICATION_INVALID_TITLE,
                ErrorCode.NOTIFICATION_INVALID_TITLE
        );
    }
}
