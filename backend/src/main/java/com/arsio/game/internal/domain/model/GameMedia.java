package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.game.internal.domain.valueobject.ObjectKey;

public class GameMedia {

    private final GameMediaId id;
    private final GameId gameId;
    private ObjectKey objectKey;
    private MediaType type;

    public GameMedia(GameMediaId id, GameId gameId, ObjectKey objectKey, MediaType type) {
        this.id = id;
        this.gameId = gameId;
        this.objectKey = objectKey;
        this.type = type;
    }

    public GameMediaId getId() {
        return id;
    }

    public GameId getGameId() {
        return gameId;
    }

    public ObjectKey getObjectKey() {
        return objectKey;
    }

    public MediaType getType() {
        return type;
    }
}
