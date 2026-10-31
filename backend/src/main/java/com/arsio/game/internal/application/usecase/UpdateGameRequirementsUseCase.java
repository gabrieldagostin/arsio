package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameRequirementNotFoundException;
import com.arsio.game.internal.application.command.UpdateGameRequirementCommand;
import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.domain.repository.GameRequirementRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateGameRequirementsUseCase {

    private final GameRequirementRepository gameRequirements;

    public UpdateGameRequirementsUseCase(GameRequirementRepository gameRequirements) {
        this.gameRequirements = gameRequirements;
    }

    public GetGameRequirementResponse execute(UpdateGameRequirementCommand command) {

        GameRequirement gameRequirement = gameRequirements.findById(command.requirementId())
                .orElseThrow(GameRequirementNotFoundException::new);

        gameRequirement.update(
                command.operatingSystemId(),
                command.processorId(),
                command.gpuId(),
                command.ram(),
                command.storage(),
                command.storageUnit()
        );

        return gameRequirements.findByGameIdAndCategory(command.gameId(), command.category());
    }
}
