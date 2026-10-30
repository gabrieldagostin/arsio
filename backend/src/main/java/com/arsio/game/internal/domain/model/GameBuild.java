package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.shared.storage.ObjectKey;

public class GameBuild {

    private final GameBuildId id;
    private final GameId gameId;
    private ObjectKey objectKey;
    private FileSize fileSize;
    private BuildStatus status;

    public GameBuild(GameBuildId id, GameId gameId, ObjectKey objectKey, FileSize fileSize, BuildStatus status) {
        this.id = id;
        this.gameId = gameId;
        this.objectKey = objectKey;
        this.fileSize = fileSize;
        this.status = status;
    }

    public static GameBuild create(GameId gameId, ObjectKey objectKey, Long value, BuildStatus status) {
        return new GameBuild(
                GameBuildId.generate(),
                gameId,
                objectKey,
                new FileSize(value),
                status
        );
    }

    public GameBuildId getId() {
        return id;
    }

    public GameId getGameId() {
        return gameId;
    }

    public ObjectKey getObjectKey() {
        return objectKey;
    }

    public FileSize getFileSize() {
        return fileSize;
    }

    public BuildStatus getStatus() {
        return status;
    }

    public void updateObjectKey(ObjectKey objectKey) {
        this.objectKey = objectKey;
    }

    public void updateStatus(BuildStatus status) {
        this.status = status;
    }
}
