package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GetGenreResponse(
        UUID id,
        String name
) {
}
