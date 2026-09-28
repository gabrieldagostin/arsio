package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.*;

public record CreateGameRequirementCommand(
        GameId gameId,
        RequirementCategory category,
        RequirementId operatingSystemId,
        RequirementId processorId,
        RequirementId gpuId,
        Ram ram,
        Storage storage,
        StorageUnit storageUnit
) {
}
