package com.arsio.user.internal.infra.persistence.repository;

import com.arsio.user.internal.infra.persistence.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProfileRepository extends JpaRepository<ProfileEntity, UUID> {

    Optional<ProfileEntity> findByUser_Id(UUID userId);
}
