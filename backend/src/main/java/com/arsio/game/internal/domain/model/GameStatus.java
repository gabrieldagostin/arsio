package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.exception.InvalidGameStatusException;

public enum GameStatus {

    DRAFT(0, "Draft"),
    PUBLISHED(1, "Published"),
    ARCHIVED(2, "Archived");

    private Integer cod;
    private String description;

    GameStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public GameStatus toEnum(Integer cod) {
        if (cod == null) return null;
        for (GameStatus gameStatus : GameStatus.values()) {
            if (cod.equals(gameStatus.cod)) return gameStatus;
        }
        throw new InvalidGameStatusException();
    }
}
