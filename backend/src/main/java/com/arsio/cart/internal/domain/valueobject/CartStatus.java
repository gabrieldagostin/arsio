package com.arsio.cart.internal.domain.valueobject;

import com.arsio.cart.internal.domain.exception.InvalidCartStatusException;

public enum CartStatus {

    ACTIVE(0, "Active"),
    ABANDONED(1, "Abandoned");

    private final Integer cod;
    private final String description;

    CartStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public CartStatus toEnum(Integer cod) {
        if (cod == null) return null;
        for (CartStatus cartStatus : CartStatus.values()) {
            if (cod.equals(cartStatus.cod)) return cartStatus;
        }
        throw new InvalidCartStatusException();
    }
}
