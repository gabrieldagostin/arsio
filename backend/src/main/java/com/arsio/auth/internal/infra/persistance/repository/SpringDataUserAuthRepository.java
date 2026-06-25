package com.arsio.auth.internal.infra.persistance.repository;

import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserAuthRepository extends JpaRepository<UserAuthEntity, UUID> {

    UserAuthEntity findByEmail(String email);
}
