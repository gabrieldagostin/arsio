package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.GpuEntity;
import com.arsio.shared.pagination.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataGpuRepository extends JpaRepository<GpuEntity, UUID> {

    Page<GpuEntity> findAllByActiveTrue(Pageable pageable);

    Page<GpuEntity> findByModelContainingIgnoreCaseAndActiveTrue(String search, Pageable pageable);

    Optional<GpuEntity> findByIdAndActiveTrue(UUID gpuId);
}
