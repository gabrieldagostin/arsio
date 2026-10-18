package com.arsio.order.internal.domain.valueobject;

import com.arsio.order.internal.domain.exception.InvalidOrderStatusException;

public enum OrderStatus {

    PENDING(0, "Pending"),
    COMPLETED(1, "Completed"),
    CANCELED(2, "Canceled"),
    FAILED(3, "Failed");

    private final Integer cod;
    private final String description;

    OrderStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public OrderStatus toEnum(Integer cod) {
        if (cod == null) return null;
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (cod.equals(orderStatus.cod)) return orderStatus;
        }
        throw new InvalidOrderStatusException();
    }
}
