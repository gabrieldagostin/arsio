package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GetGameRequirementResponse(
        UUID id,
        UUID gameId,
        String category,
        String operatingSystemName,
        String processorManufacturerName,
        String processorModel,
        String gpuManufacturerName,
        String gpuModel,
        Integer ram,
        Double storage,
        String storageUnit
) {}
