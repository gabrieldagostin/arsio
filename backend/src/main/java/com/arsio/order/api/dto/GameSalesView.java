package com.arsio.order.api.dto;

import java.util.UUID;

public record GameSalesView(
        UUID gameId,
        Long totalSold
) {
}
