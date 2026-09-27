package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataGenreRepository extends JpaRepository<GenreEntity, UUID> {

    Page<GenreEntity> findAllByActiveTrue(Pageable pageable);

    Page<GenreEntity> findByNameContainingIgnoreCaseAndActiveTrue(String search, Pageable pageable);

    Set<GenreEntity> findAllByIdAndActiveTrue(Set<UUID> ids);

    Optional<GenreEntity> findByIdAndActiveTrue(UUID value);
}
