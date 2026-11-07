package com.arsio.wishlist.internal.application.command;

import java.util.UUID;

public record DeleteWishlistGameCommand(
        UUID userId,
        UUID gameId
) {
}
