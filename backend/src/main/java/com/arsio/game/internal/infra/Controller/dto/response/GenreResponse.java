package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GenreResponse(
        UUID id,
        String name
) {
}
