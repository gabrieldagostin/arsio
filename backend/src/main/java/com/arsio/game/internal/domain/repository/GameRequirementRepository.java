package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;

public interface GameRequirementRepository {

    GetGameRequirementResponse save(GameRequirement gameRequirement);
}
