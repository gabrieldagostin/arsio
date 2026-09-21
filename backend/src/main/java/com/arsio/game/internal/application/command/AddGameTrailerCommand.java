package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GameId;

public record AddGameTrailerCommand(
        GameId gameId,
        String externalUrl
) {
}
