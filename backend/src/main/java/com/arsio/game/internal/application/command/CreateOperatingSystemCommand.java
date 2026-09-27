package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Model;

public record CreateOperatingSystemCommand(
        Model name
) {
}
