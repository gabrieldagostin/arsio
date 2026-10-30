package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.shared.storage.ObjectKey;

public class GameBuild {

    private final GameBuildId id;
    private final GameId gameId;
    private GameVersion version;
    private ObjectKey objectKey;
    private FileSize fileSize;
    private BuildStatus status;

    public GameBuild(GameBuildId id, GameId gameId, GameVersion version, ObjectKey objectKey, FileSize fileSize, BuildStatus status) {
        this.id = id;
        this.gameId = gameId;
        this.version = version;
        this.objectKey = objectKey;
        this.fileSize = fileSize;
        this.status = status;
    }

    public GameBuildId getId() {
        return id;
    }

    public GameId getGameId() {
        return gameId;
    }

    public GameVersion getVersion() {
        return version;
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
}
