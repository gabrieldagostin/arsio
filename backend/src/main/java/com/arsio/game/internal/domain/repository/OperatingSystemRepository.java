package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

public interface OperatingSystemRepository {

    PageResult<OperatingSystem> findAll(String search, Pagination pagination);
}
