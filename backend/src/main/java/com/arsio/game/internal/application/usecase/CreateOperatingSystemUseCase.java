package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateOperatingSystemCommand;
import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateOperatingSystemUseCase {

    private final OperatingSystemRepository operatingSystems;

    public CreateOperatingSystemUseCase(OperatingSystemRepository operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public GetOperatingSystemResponse execute(CreateOperatingSystemCommand command) {

        OperatingSystem operatingSystem = OperatingSystem.create(command.name());

        operatingSystems.save(operatingSystem);

        return new GetOperatingSystemResponse(
                operatingSystem.getId().value(),
                operatingSystem.getName().value()
        );
    }
}
