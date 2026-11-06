package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.valueobject.GameId;

import java.util.Optional;

public interface DiscountRepository {

    void save(Discount discount);

    Optional<Discount> findByGameId(GameId gameId);

    Optional<Discount> findScheduledByGameId(GameId gameId);
}
