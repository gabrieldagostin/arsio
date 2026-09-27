package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.domain.valueobject.GenreName;

public record UpdateGenreCommand(
        GenreId genreId,
        GenreName name
) {
}
