package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GenreName;

public record CreateGenreCommand(
        GenreName name
) {
}
