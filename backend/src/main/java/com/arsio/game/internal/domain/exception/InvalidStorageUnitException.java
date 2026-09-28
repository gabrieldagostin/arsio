package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidStorageUnitException extends ValidationException {

    public InvalidStorageUnitException() {
        super(
                "Invalid storage unit, please try again.",
                ErrorTypes.GAME_INVALID_STORAGE_UNIT,
                ErrorCode.GAME_INVALID_STORAGE_UNIT
        );
    }

    public InvalidStorageUnitException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_STORAGE_UNIT,
                ErrorCode.GAME_INVALID_STORAGE_UNIT
        );
    }
}
