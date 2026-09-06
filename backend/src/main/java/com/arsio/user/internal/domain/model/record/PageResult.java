package com.arsio.user.internal.domain.model.record;

import java.util.List;
import java.util.function.Function;

public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {

    public int totalPages() {
        return (int) Math.ceil((double) totalElements / size);
    }

    public <R> PageResult<R> map(Function<T, R> mapper) {
        return new PageResult<>(
                content.stream()
                        .map(mapper)
                        .toList(),
                page,
                size,
                totalElements,
                totalPages
        );
    }
}
