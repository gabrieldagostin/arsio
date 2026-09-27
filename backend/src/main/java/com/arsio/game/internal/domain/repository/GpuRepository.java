package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.util.Optional;

public interface GpuRepository {

    PageResult<Gpu> findAll(String search, Pagination pagination);

    void save(Gpu gpu);

    Optional<Gpu> findById(RequirementId requirementId);
}
