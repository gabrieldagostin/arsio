package com.arsio.shared.pagination;

public record Pagination(
        int page,
        int size,
        String sort,
        SortDirection direction
) {}
