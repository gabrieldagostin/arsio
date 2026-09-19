package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Tag;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

public interface TagRepository {

    PageResult<Tag> findAll(String search, Pagination pagination);
}
