package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;

public record CreateGpuCommand(
        Manufacturer manufacturer,
        Model model
) {
}
