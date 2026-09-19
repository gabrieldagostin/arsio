package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GenreId;

public record DeleteGenreFromGameCommand(
        GameId gameId,
        GenreId genreId
) {
}
