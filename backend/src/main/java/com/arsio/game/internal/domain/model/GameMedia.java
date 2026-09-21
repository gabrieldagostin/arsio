package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.shared.storage.ObjectKey;

public class GameMedia {

    private final GameMediaId id;
    private final GameId gameId;
    private ObjectKey objectKey;
    private MediaType type;
    private MediaRole role;
    private ExternalUrl externalUrl;

    public GameMedia(GameMediaId id, GameId gameId, ObjectKey objectKey, MediaType type, MediaRole role, ExternalUrl externalUrl) {
        this.id = id;
        this.gameId = gameId;
        this.objectKey = objectKey;
        this.type = type;
        this.role = role;
        this.externalUrl = externalUrl;
    }

    public static GameMedia create(GameId gameId, ObjectKey objectKey, MediaType type, MediaRole role) {
        return new GameMedia(
                GameMediaId.generate(),
                gameId,
                objectKey,
                type,
                role,
                null
        );
    }

    public static GameMedia createVideo(GameId gameId, MediaType type, MediaRole role, String externalUrl) {
        return new GameMedia(
                GameMediaId.generate(),
                gameId,
                null,
                type,
                role,
                new ExternalUrl(externalUrl)
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

    public ExternalUrl getExternalUrl() {
        return externalUrl;
    }
}
