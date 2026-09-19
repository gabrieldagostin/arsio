package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.TagId;

public record DeleteTagFromGameCommand(
        GameId gameId,
        TagId tagId
) {
}
