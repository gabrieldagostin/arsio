package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GpuNotFoundException extends NotFoundException {

    public GpuNotFoundException() {
        super(
                "Gpu not found,  please try again.",
                ErrorTypes.GAME_GPU_NOT_FOUND,
                ErrorCode.GAME_GPU_NOT_FOUND
        );
    }

    public GpuNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_GPU_NOT_FOUND,
                ErrorCode.GAME_GPU_NOT_FOUND
        );
    }
}
