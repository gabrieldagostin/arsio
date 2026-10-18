package com.arsio.order.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidOrderStatusException extends ValidationException {

    public InvalidOrderStatusException() {
        super(
                "Invalid order status, please try again.",
                ErrorTypes.ORDER_INVALID_STATUS,
                ErrorCode.ORDER_INVALID_STATUS
        );
    }

    public InvalidOrderStatusException(String message) {
        super(
                message,
                ErrorTypes.ORDER_INVALID_STATUS,
                ErrorCode.ORDER_INVALID_STATUS
        );
    }
}
