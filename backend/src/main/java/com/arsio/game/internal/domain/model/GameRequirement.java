package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.*;

public class GameRequirement {

    private final RequirementId id;
    private final GameId gameId;
    private RequirementCategory category;
    private RequirementId operatingSystemId;
    private RequirementId processorId;
    private RequirementId gpuId;
    private Ram ram;
    private Storage storage;
    private StorageUnit storageUnit;

    public GameRequirement(RequirementId id, GameId gameId, RequirementCategory category, RequirementId operatingSystemId, RequirementId processorId, RequirementId gpuId, Ram ram, Storage storage, StorageUnit storageUnit) {
        this.id = id;
        this.gameId = gameId;
        this.category = category;
        this.operatingSystemId = operatingSystemId;
        this.processorId = processorId;
        this.gpuId = gpuId;
        this.ram = ram;
        this.storage = storage;
        this.storageUnit = storageUnit;
    }

    public RequirementId getId() {
        return id;
    }

    public GameId getGameId() {
        return gameId;
    }

    public RequirementCategory getCategory() {
        return category;
    }

    public RequirementId getOperatingSystemId() {
        return operatingSystemId;
    }

    public RequirementId getProcessorId() {
        return processorId;
    }

    public RequirementId getGpuId() {
        return gpuId;
    }

    public Ram getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public StorageUnit getStorageUnit() {
        return storageUnit;
    }
}
