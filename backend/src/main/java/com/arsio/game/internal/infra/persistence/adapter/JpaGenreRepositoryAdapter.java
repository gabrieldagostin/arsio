package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.infra.persistence.entity.GenreEntity;
import com.arsio.game.internal.infra.persistence.mapper.GenreEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGenreRepository;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaGenreRepositoryAdapter implements GenreRepository {

    private final GenreEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataGenreRepository genres;

    @Override
    public PageResult<Genre> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<GenreEntity> result;

        if (search == null || search.isBlank()) {
            result = genres.findAllByActiveTrue(pageable);
        } else {
            result = genres.findByNameContainingIgnoreCaseAndActiveTrue(search.trim(), pageable);
        }

        List<Genre> genreList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                genreList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public Set<Genre> findAllById(Set<GenreId> genreIds) {

        Set<UUID> ids = genreIds.stream()
                .map(GenreId::value)
                .collect(Collectors.toSet());

        return genres.findAllByIdInAndActiveTrue(ids)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Genre genre) {
        genres.save(mapper.toEntity(genre));
    }

    @Override
    public Optional<Genre> findById(GenreId genreId) {
        return genres.findByIdAndActiveTrue(genreId.value())
                .map(mapper::toDomain);
    }

    @Override
    public Set<Genre> findAllGenresByGameId(GameId gameId) {
        return genres.findAllGenresByGameId(gameId.value())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toSet());
    }
}
