package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidBuildStatusException;

public enum BuildStatus {

    SUPERSEDED(0, "Superseded"),
    READY(1, "Ready"),
    PUBLISHED(2, "Published");

    private final Integer cod;
    private final String description;

    BuildStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public BuildStatus toEnum(Integer cod) {
        if (cod == null) return null;
        for (BuildStatus buildStatus : BuildStatus.values()) {
            if (cod.equals(buildStatus.cod)) return buildStatus;
        }
        throw new InvalidBuildStatusException();
    }
}
