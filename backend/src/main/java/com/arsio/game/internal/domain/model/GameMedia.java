package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.shared.storage.ObjectKey;

public class GameMedia {

    private final GameMediaId id;
    private final GameId gameId;
    private ObjectKey objectKey;
    private MediaType type;
    private MediaRole role;

    public GameMedia(GameMediaId id, GameId gameId, ObjectKey objectKey, MediaType type, MediaRole role) {
        this.id = id;
        this.gameId = gameId;
        this.objectKey = objectKey;
        this.type = type;
        this.role = role;
    }

    public static GameMedia create(GameId gameId, ObjectKey objectKey, MediaType type, MediaRole role) {
        return new GameMedia(
                GameMediaId.generate(),
                gameId,
                objectKey,
                type,
                role
        );
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

    public MediaRole getRole() {
        return role;
    }
}
