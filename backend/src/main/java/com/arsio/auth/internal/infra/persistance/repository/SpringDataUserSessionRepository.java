package com.arsio.auth.internal.infra.persistance.repository;

import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserSessionRepository extends JpaRepository<UserSessionEntity, UUID> {

    Optional<UserSessionEntity> findByIdAndRevokedFalse(UUID id);
}
