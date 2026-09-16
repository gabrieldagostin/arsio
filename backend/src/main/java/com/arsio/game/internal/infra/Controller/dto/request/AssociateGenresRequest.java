package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AssociateGenresRequest(
        @NotEmpty
        Set<UUID> genreIds
) {
}
