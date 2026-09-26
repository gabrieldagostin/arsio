package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.ProcessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataProcessorRepository extends JpaRepository<ProcessorEntity, UUID> {

    Page<ProcessorEntity> findByModelContainingIgnoreCaseAndActiveTrue(String search, Pageable pageable);
}
