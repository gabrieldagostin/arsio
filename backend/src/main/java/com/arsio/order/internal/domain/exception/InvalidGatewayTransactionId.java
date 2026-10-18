package com.arsio.order.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGatewayTransactionId extends ValidationException {

    public InvalidGatewayTransactionId() {
        super(
                "Invalid gateway transaction id, please try again.",
                ErrorTypes.ORDER_INVALID_GATEWAY_TRANSACTION_ID,
                ErrorCode.ORDER_INVALID_GATEWAY_TRANSACTION_ID
        );
    }

    public InvalidGatewayTransactionId(String message) {
        super(
                message,
                ErrorTypes.ORDER_INVALID_GATEWAY_TRANSACTION_ID,
                ErrorCode.ORDER_INVALID_GATEWAY_TRANSACTION_ID
        );
    }
}
