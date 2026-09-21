package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;

import java.util.Optional;

public interface GameMediaRepository {

    void save(GameMedia gameMedia);

    Optional<GameMedia> findById(GameMediaId gameMediaId);

    Optional<GameMedia> findByGameId(GameId gameId);

    void deleteById(GameMediaId gameMediaId);
}
