package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;

import java.util.Optional;
import java.util.Set;

public interface GameMediaRepository {

    void save(GameMedia gameMedia);

    Optional<GameMedia> findById(GameMediaId gameMediaId);

    Set<GameMedia> findAllByGameId(GameId gameId);

    void deleteById(GameMediaId gameMediaId);

    Optional<GameMedia> findByGameIdAndRoleThumbnail(GameId gameId);
}
