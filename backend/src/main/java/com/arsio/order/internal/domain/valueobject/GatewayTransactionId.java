package com.arsio.order.internal.domain.valueobject;

import com.arsio.order.internal.domain.exception.InvalidGatewayTransactionId;

public record GatewayTransactionId(String value) {

    public GatewayTransactionId {
        validate(value);
    }

    private void validate(String value) {

        if (value == null || value.isBlank())
            throw new InvalidGatewayTransactionId();
    }

    @Override
    public String toString() {
        return "GatewayTransactionId[PROTECTED]";
    }
}
