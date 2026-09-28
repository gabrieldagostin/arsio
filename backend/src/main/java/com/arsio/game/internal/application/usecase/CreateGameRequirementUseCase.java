package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateGameRequirementCommand;
import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.domain.repository.GameRequirementRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateGameRequirementUseCase {

    private final GameRequirementRepository gameRequirements;

    public CreateGameRequirementUseCase(GameRequirementRepository gameRequirements) {
        this.gameRequirements = gameRequirements;
    }

    public GetGameRequirementResponse execute(CreateGameRequirementCommand command) {

        GameRequirement gameRequirement = GameRequirement.create(
                command.gameId(),
                command.category(),
                command.operatingSystemId(),
                command.processorId(),
                command.gpuId(),
                command.ram(),
                command.storage(),
                command.storageUnit()
        );

        return gameRequirements.save(gameRequirement);
    }
}
