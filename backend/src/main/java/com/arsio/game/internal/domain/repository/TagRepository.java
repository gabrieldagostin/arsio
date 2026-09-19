package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.util.Set;

public interface TagRepository {

    PageResult<Tag> findAll(String search, Pagination pagination);

    Set<Tag> findAllByIds(Set<TagId> tagIds);
}
