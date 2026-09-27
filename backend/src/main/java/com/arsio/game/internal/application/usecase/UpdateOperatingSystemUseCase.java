package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.OperatingSystemNotFoundException;
import com.arsio.game.internal.application.command.UpdateOperatingSystemCommand;
import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateOperatingSystemUseCase {

    private final OperatingSystemRepository operatingSystems;

    public UpdateOperatingSystemUseCase(OperatingSystemRepository operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public GetOperatingSystemResponse execute(UpdateOperatingSystemCommand command) {

        OperatingSystem operatingSystem = operatingSystems.findById(command.operatingSystemId())
                .orElseThrow(OperatingSystemNotFoundException::new);

        operatingSystem.update(command.name());

        operatingSystems.save(operatingSystem);

        return new GetOperatingSystemResponse(
                operatingSystem.getId().value(),
                operatingSystem.getName().value()
        );
    }
}
