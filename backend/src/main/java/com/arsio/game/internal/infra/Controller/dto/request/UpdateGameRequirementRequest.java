package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateGameRequirementRequest(

        @NotNull(message = "{requirementId.require}")
        UUID requirementId,

        @NotBlank(message = "{category.require}")
        String category,

        UUID operatingSystemId,

        UUID processorId,

        UUID gpuId,

        @Positive(message = "{ram.positive}")
        Integer ram,

        @Positive(message = "{storage.positive}")
        Double storage,

        @Size(min = 2, max = 2, message = "{storageUnit.size}")
        String storageUnit
) {
}
