package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.util.Optional;

public interface ProcessorRepository {

    PageResult<Processor> findAll(String search, Pagination pagination);

    void save(Processor processor);

    Optional<Processor> findById(RequirementId processorId);
}
