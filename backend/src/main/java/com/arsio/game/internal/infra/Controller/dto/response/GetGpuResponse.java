package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GetGpuResponse(
        UUID id,
        String model,
        String manufacturer
) {
}
