package com.arsio.game.internal.domain.valueobject;

public record ExternalUrl(String value) {

    public ExternalUrl {
        value = value == null ? null : value.trim();
    }
}
