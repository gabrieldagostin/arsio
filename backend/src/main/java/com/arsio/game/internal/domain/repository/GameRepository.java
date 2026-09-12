package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Game;

public interface GameRepository {

    void save(Game game);
}
