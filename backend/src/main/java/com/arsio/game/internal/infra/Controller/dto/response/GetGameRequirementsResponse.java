package com.arsio.game.internal.infra.Controller.dto.response;

public record GetGameRequirementsResponse(
        GetGameRequirementResponse minimumGameRequirement,
        GetGameRequirementResponse recommendedGameRequirement
) {
}
