package com.arsio.notification.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidMessageException extends ValidationException {

    public InvalidMessageException() {
        super(
                "Invalid message.",
                ErrorTypes.NOTIFICATION_INVALID_MESSAGE,
                ErrorCode.NOTIFICATION_INVALID_MESSAGE
        );
    }

    public InvalidMessageException(String message) {
        super(
                message,
                ErrorTypes.NOTIFICATION_INVALID_MESSAGE,
                ErrorCode.NOTIFICATION_INVALID_MESSAGE
        );
    }
}
