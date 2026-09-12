package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.infra.persistence.mapper.GenreEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGenreRepositoryAdapter implements GenreRepository {

    private final GenreEntityMapper mapper;
    private final SpringDataGenreRepository genres;
}
