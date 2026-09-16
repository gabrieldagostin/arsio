package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GenreId;

import java.util.Set;

public record AssociateGenresCommand(
        Set<GenreId> genreIds
) {
}
