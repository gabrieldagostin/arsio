package com.arsio.game.internal.domain.valueobject;

public record Manufacturer(String value) {

    public Manufacturer {
        value = value == null ? null : value.trim();
    }
}
