package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;

import java.util.Optional;

public interface GameRequirementRepository {

    GetGameRequirementResponse save(GameRequirement gameRequirement);

    GetGameRequirementResponse findByGameIdAndCategory(GameId gameId, RequirementCategory category);

    Optional<GameRequirement> findById(RequirementId requirementId);
}
