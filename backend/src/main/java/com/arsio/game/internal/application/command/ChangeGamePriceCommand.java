package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.shared.money.Money;

public record ChangeGamePriceCommand(
        GameId gameId,
        Money newPrice
) {
}
