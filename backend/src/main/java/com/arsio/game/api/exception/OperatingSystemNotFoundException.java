package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class OperatingSystemNotFoundException extends NotFoundException {

    public OperatingSystemNotFoundException() {
        super(
                "Operating System not found,  please try again.",
                ErrorTypes.GAME_OPERATING_SYSTEM_NOT_FOUND,
                ErrorCode.GAME_OPERATING_SYSTEM_NOT_FOUND
        );
    }

    public OperatingSystemNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_OPERATING_SYSTEM_NOT_FOUND,
                ErrorCode.GAME_OPERATING_SYSTEM_NOT_FOUND
        );
    }
}
