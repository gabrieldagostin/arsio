package com.arsio.auth.internal.infra.persistance.repository;

import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserAuthRepository extends JpaRepository<UserAuthEntity, UUID> {

    Optional<UserAuthEntity> findByEmail(String email);

    Optional<UserAuthEntity> findByUsernameNormalized(String username);
}
