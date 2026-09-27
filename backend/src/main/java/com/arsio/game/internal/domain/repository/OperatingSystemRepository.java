package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.util.Optional;

public interface OperatingSystemRepository {

    PageResult<OperatingSystem> findAll(String search, Pagination pagination);

    void save(OperatingSystem operatingSystem);

    Optional<OperatingSystem> findById(RequirementId operatingSystemId);
}
