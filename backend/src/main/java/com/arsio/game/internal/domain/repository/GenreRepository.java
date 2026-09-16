package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Genre;
import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.util.Set;

public interface GenreRepository {

    PageResult<Genre> findAll(String search, Pagination pagination);

    Set<Genre> findAllByIds(Set<GenreId> genreIds);
}
