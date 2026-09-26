package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

public interface GpuRepository {

    PageResult<Gpu> findAll(String search, Pagination pagination);
}
