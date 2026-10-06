package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import com.arsio.game.internal.infra.persistence.entity.GameRequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataGameRequirementRepository extends JpaRepository<GameRequirementEntity, UUID> {

    GameRequirementEntity findByGameIdAndCategory(UUID gameId, RequirementCategory category);
}
