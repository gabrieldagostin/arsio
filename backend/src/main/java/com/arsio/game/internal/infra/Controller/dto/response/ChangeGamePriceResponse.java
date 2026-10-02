package com.arsio.game.internal.infra.Controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ChangeGamePriceResponse(
        UUID gameId,
        BigDecimal newPrice
) {
}
