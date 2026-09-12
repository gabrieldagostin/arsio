package com.arsio.auth.internal.infra.persistence.repository;

import com.arsio.auth.internal.infra.persistence.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserSessionRepository extends JpaRepository<UserSessionEntity, UUID> {

    Optional<UserSessionEntity> findByIdAndRevokedFalse(UUID id);

    @Modifying
    @Query("""
        UPDATE UserSessionEntity s
            SET s.revoked = true,
                s.revokedAt = CURRENT_TIMESTAMP
        WHERE s.user.id = :userId
    """)
    int revokeAllSessionsByUserId(UUID userId);
}
