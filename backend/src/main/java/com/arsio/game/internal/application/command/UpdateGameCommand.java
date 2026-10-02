package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Description;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameTitle;

import java.time.LocalDate;

public record UpdateGameCommand(
        GameId gameId,
        GameTitle title,
        Description description,
        LocalDate releaseDate
) {
}
