package com.arsio.auth.internal.infra.persistence.repository;

import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserAuthRepository extends JpaRepository<UserAuthEntity, UUID> {

    Optional<UserAuthEntity> findByEmail(String email);

    Optional<UserAuthEntity> findByUsername(String username);
}
