package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.infra.persistence.entity.GameMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataGameMediaRepository extends JpaRepository<GameMediaEntity, UUID> {

    Set<GameMediaEntity> findAllByGameId(UUID gameId);

    Optional<GameMediaEntity> findByGameIdAndRole(UUID value, MediaRole role);
}
