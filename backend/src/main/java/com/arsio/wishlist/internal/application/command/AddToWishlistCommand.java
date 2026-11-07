package com.arsio.wishlist.internal.application.command;

import java.util.UUID;

public record AddToWishlistCommand(
        UUID userId,
        UUID gameId
) {
}
