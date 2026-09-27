package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.OperatingSystemNotFoundException;
import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteOperatingSystemUseCase {

    private final OperatingSystemRepository operatingSystems;

    public DeleteOperatingSystemUseCase(OperatingSystemRepository operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public void execute(UUID value) {

        RequirementId operatingSystemId = new RequirementId(value);

        OperatingSystem operatingSystem = operatingSystems.findById(operatingSystemId)
                .orElseThrow(OperatingSystemNotFoundException::new);

        operatingSystem.deactivate();

        operatingSystems.save(operatingSystem);
    }
}
