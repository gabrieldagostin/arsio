package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.Description;
import com.arsio.game.internal.domain.valueobject.GameTitle;
import com.arsio.shared.money.Money;

import java.time.LocalDate;

public record CreateGameCommand(
        GameTitle title,
        Description description,
        Money basePrice,
        LocalDate releaseDate
) {
}
