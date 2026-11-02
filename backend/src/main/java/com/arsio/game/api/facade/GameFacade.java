package com.arsio.game.api.facade;

import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.api.dto.GetDiscountedGameView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameFacade {

    List<GetDeveloperGamesView> findByDeveloperId(UUID developerId);

    Optional<GetDiscountedGameView> applyGameDiscount(UUID gameId, BigDecimal percentage, LocalDate startDate, LocalDate endDate);
}
