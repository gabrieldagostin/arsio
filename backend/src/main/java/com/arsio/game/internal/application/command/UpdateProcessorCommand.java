package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public record UpdateProcessorCommand(
        RequirementId processorId,
        Model model,
        Manufacturer manufacturer
) {
}
