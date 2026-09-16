package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.repository.GenreRepository;
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
            result = genres.findAll(pageable);
        } else {
            result = genres.findByNameContainingIgnoreCase(search.trim(), pageable);
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
}
