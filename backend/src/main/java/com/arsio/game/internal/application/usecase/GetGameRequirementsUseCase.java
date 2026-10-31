package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GameRequirementRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementsResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetGameRequirementsUseCase {

    private final GameRequirementRepository gameRequirements;

    public GetGameRequirementsUseCase(GameRequirementRepository gameRequirements) {
        this.gameRequirements = gameRequirements;
    }

    public GetGameRequirementsResponse execute(UUID value) {

        GameId gameId = new GameId(value);

        GetGameRequirementResponse minimumGameRequirement = gameRequirements.findByGameIdAndCategory(gameId, RequirementCategory.MINIMUM);

        GetGameRequirementResponse recommendedGameRequirement = gameRequirements.findByGameIdAndCategory(gameId, RequirementCategory.RECOMMENDED);

        return new GetGameRequirementsResponse(
                minimumGameRequirement,
                recommendedGameRequirement
        );
    }
}
