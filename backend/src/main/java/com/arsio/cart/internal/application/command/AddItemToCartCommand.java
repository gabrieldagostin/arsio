package com.arsio.cart.internal.application.command;

import java.util.UUID;

public record AddItemToCartCommand(
        UUID userId,
        UUID gameId
) {
}
