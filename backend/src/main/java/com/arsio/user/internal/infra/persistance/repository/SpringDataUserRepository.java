package com.arsio.user.internal.infra.persistance.repository;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserAuthenticationData> findUserAuthenticationDataById(UUID id);

    Optional<UserAuthenticationData> findUserAuthenticationDataByUsername(String username);
}
