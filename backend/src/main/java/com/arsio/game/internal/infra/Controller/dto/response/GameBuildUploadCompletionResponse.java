package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.UUID;

public record GameBuildUploadCompletionResponse(
        UUID id,
        Long fileSize,
        String Status
) {
}
