package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Processor;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

public interface ProcessorRepository {

    PageResult<Processor> findAll(String search, Pagination pagination);

    void save(Processor processor);
}
