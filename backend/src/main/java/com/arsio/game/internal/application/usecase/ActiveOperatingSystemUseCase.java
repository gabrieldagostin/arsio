package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.OperatingSystemNotFoundException;
import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ActiveOperatingSystemUseCase {

    private final OperatingSystemRepository operatingSystems;

    public ActiveOperatingSystemUseCase(OperatingSystemRepository operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public GetOperatingSystemResponse execute(UUID value) {

        RequirementId operatingSystemId = new RequirementId(value);

        OperatingSystem operatingSystem = operatingSystems.findById(operatingSystemId)
                .orElseThrow(OperatingSystemNotFoundException::new);

        operatingSystem.activate();

        operatingSystems.save(operatingSystem);

        return new GetOperatingSystemResponse(
                operatingSystem.getId().value(),
                operatingSystem.getName().value()
        );
    }
}
