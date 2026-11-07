package com.arsio.cart.internal.application.command;

import java.util.UUID;

public record RemoveItemFromCartCommand(
        UUID userId,
        UUID gameId
) {
}
