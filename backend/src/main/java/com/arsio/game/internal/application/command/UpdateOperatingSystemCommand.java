package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public record UpdateOperatingSystemCommand(
        RequirementId operatingSystemId,
        Model name
) {
}
