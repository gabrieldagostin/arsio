package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GameMediaId;

public record DeleteScreenshotCommand(
        GameMediaId imageId
) {
}
