package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidMediaRoleException;

public enum MediaRole {

    THUMBNAIL(0, "Thumbnail"),
    BANNER(1, "Banner"),
    SCREENSHOT(2, "Screenshot"),
    TRAILER(3, "Trailer"),;

    private final Integer cod;
    private final String description;

    MediaRole(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public MediaRole toEnum(Integer cod) {
        if (cod == null) return null;
        for (MediaRole mediaRole : MediaRole.values()) {
            if (cod.equals(mediaRole.cod)) return mediaRole;
        }
        throw new InvalidMediaRoleException();
    }
}
