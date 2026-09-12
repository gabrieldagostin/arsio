package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidMediaTypeException;

public enum MediaType {

    IMAGE(0, "Image"),
    VIDEO(1, "Video");

    private final Integer cod;
    private final String description;

    MediaType(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public MediaType toEnum(Integer cod) {
        if (cod == null) return null;
        for (MediaType mediaType : MediaType.values()) {
            if (cod.equals(mediaType.cod)) return mediaType;
        }
        throw new InvalidMediaTypeException();
    }
}
