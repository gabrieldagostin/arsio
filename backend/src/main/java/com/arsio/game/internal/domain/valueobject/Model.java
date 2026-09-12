package com.arsio.game.internal.domain.valueobject;

public record Model(String value) {

    public Model {
        value = value == null ? null : value.trim();
    }
}
