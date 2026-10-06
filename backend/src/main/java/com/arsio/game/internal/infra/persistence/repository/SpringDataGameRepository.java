package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.domain.model.GameStatus;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataGameRepository extends JpaRepository<GameEntity, UUID> {

    Page<GameEntity> findAllByStatus(GameStatus status, Pageable pageable);

    Page<GameEntity> findByTitleContainingIgnoreCaseAndStatus(String search, GameStatus status, Pageable pageable);

    Page<GameEntity> findAllByFeaturedTrue(Pageable pageable);
}
