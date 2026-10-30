package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.persistence.entity.GameBuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataGameBuildRepository extends JpaRepository<GameBuildEntity, UUID> {

    Optional<GameBuildEntity> findByGameId(GameId gameId);
}
