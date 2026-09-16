package com.arsio.developer.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class DeveloperNotFoundException extends NotFoundException {

    public DeveloperNotFoundException() {
        super(
                "Developer not found.",
                ErrorTypes.DEVELOPER_NOT_FOUND,
                ErrorCode.DEVELOPER_NOT_FOUND
        );
    }

    public DeveloperNotFoundException(String message) {
        super(
                message,
                ErrorTypes.DEVELOPER_NOT_FOUND,
                ErrorCode.DEVELOPER_NOT_FOUND
        );
    }
}
