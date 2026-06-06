package com.arsio.auth.internal.infra.persistance.repository;

import com.arsio.auth.internal.infra.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsernameNormalized(String username);

    UserDetails findUserDetailsByUsernameNormalized(String Username);

    UserEntity findUserByUsernameNormalized(String username);
}
