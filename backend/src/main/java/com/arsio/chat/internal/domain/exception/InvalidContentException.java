package com.arsio.chat.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidContentException extends ValidationException {

    public InvalidContentException() {
        super(
                "Invalid content, please try again.",
                ErrorTypes.CHAT_INVALID_CONTENT,
                ErrorCode.CHAT_INVALID_CONTENT
        );
    }

    public InvalidContentException(String message) {
        super(
                message,
                ErrorTypes.CHAT_INVALID_CONTENT,
                ErrorCode.CHAT_INVALID_CONTENT
        );
    }
}
