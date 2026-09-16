package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.valueobject.GameId;

import java.util.Optional;

public interface GameRepository {

    void save(Game game);

    Optional<Game> findById(GameId gameId);
}
