package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.GameBuild;
import com.arsio.game.internal.domain.valueobject.GameId;

import java.util.Optional;

public interface GameBuildRepository {

    Optional<GameBuild> findByGameId(GameId gameId);

    void save(GameBuild gameBuild);
}
