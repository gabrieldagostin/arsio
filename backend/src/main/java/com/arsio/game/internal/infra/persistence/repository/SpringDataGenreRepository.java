package com.arsio.game.internal.infra.persistence.repository;

import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataGenreRepository extends JpaRepository<GenreEntity, UUID> {

    Page<GenreEntity> findAllByActiveTrue(Pageable pageable);

    Page<GenreEntity> findByNameContainingIgnoreCaseAndActiveTrue(String search, Pageable pageable);

    Set<GenreEntity> findAllByIdInAndActiveTrue(Set<UUID> ids);

    Optional<GenreEntity> findByIdAndActiveTrue(UUID genreId);

    @Query("""
        SELECT genre
        FROM GameEntity game
        JOIN game.genres genre
        WHERE game.id = :gameId
    """)
    Set<GenreEntity> findAllGenresByGameId(@Param("gameId") UUID gameId);
}
