package com.arsio.user.internal.domain.model.record;

import com.arsio.user.internal.domain.model.enums.SortDirection;

public record Pagination(
        int page,
        int size,
        String sort,
        SortDirection direction
) {}
