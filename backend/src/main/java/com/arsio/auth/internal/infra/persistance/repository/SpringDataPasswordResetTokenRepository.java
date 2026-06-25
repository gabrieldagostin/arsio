package com.arsio.auth.internal.infra.persistance.repository;

import com.arsio.auth.internal.infra.persistance.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, UUID> {

    PasswordResetTokenEntity findByPasswordToken(String token);
}
