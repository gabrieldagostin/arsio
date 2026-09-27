package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class ProcessorNotFoundException extends NotFoundException {

    public ProcessorNotFoundException() {
        super(
                "Processor not found,  please try again.",
                ErrorTypes.GAME_PROCESSOR_NOT_FOUND,
                ErrorCode.GAME_PROCESSOR_NOT_FOUND
        );
    }

    public ProcessorNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_PROCESSOR_NOT_FOUND,
                ErrorCode.GAME_PROCESSOR_NOT_FOUND
        );
    }
}
