package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record AssociateTagsRequest(

        @NotNull(message = "{tagId.require}")
        Set<UUID> tagIds
) {
}
