package com.arsio.cart.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddItemToCartRequest(

        @NotNull(message = "{gameId.require}")
        UUID gameId
) {
}
