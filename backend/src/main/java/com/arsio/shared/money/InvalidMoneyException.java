package com.arsio.shared.money;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidMoneyException extends ValidationException {

    public InvalidMoneyException() {
        super(
                "Invalid money, try again.",
                ErrorTypes.INVALID_MONEY,
                ErrorCode.INVALID_MONEY
        );
    }

    public InvalidMoneyException(String message) {
        super(
                message,
                ErrorTypes.INVALID_MONEY,
                ErrorCode.INVALID_MONEY
        );
    }
}
