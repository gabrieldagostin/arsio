package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GetProcessorResponse(
        UUID id,
        String manufacturer,
        String model
) {
}
