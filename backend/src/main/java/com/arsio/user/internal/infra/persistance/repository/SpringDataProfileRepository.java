package com.arsio.user.internal.infra.persistance.repository;

import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistance.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProfileRepository extends JpaRepository<ProfileEntity, UUID> {

    Optional<ProfileEntity> findByUserId(UserId userId);
}
