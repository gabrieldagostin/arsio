package com.arsio.user.internal.infra.persistance.repository;

import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsernameNormalized(String username);

    Optional<UserDetails> findUserDetailsByUsernameNormalized(String Username);

    Optional<UserEntity> findUserByUsernameNormalized(String username);
}
