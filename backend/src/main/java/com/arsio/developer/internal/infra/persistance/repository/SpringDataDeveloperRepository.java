package com.arsio.developer.internal.infra.persistance.repository;

import com.arsio.developer.internal.infra.persistance.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataDeveloperRepository extends JpaRepository<DeveloperEntity, UUID> {
}
