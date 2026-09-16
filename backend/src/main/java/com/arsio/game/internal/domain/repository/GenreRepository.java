package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Genre;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

public interface GenreRepository {

    PageResult<Genre> findAll(String search, Pagination pagination);
}
