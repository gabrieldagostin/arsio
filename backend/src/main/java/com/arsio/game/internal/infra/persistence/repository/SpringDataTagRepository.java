package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.TagEntity;
import com.arsio.shared.pagination.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataTagRepository extends JpaRepository<TagEntity, UUID> {

    Page<TagEntity> findAllByActiveTrue(Pageable pageable);

    Page<TagEntity> findByNameContainingIgnoreCaseAndActiveTrue(String search, Pageable pageable);

    Set<TagEntity> findAllByIdAndActiveTrue(Set<UUID> tagIds);

    Optional<TagEntity> findByIdAndActiveTrue(UUID tagId);
}
