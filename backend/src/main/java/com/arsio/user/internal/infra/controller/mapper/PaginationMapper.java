package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.domain.model.enums.SortDirection;
import com.arsio.user.internal.domain.model.record.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface PaginationMapper {

    @Mapping(target = "page", source = "pageNumber")
    @Mapping(target = "size", source = "pageSize")
    @Mapping(target = "sort", expression = "java(getSort(pageable))")
    @Mapping(target = "direction", expression = "java(getDirection(pageable))")
    Pagination toPagination(Pageable pageable);

    default Pageable toPageable(Pagination pagination) {
        Sort sort = Sort.by(
                pagination.direction() == SortDirection.ASC
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC,
                pagination.sort()
        );

        return PageRequest.of(
                pagination.page(),
                pagination.size(),
                sort
        );
    }

    default String getSort(Pageable pageable) {
        return pageable.getSort()
                .stream()
                .findFirst()
                .map(Sort.Order::getProperty)
                .orElse("id");
    }

    default SortDirection getDirection(Pageable pageable) {
        return pageable.getSort()
                .stream()
                .findFirst()
                .filter(order -> order.getDirection() != Sort.Direction.ASC)
                .map(order -> SortDirection.DESC)
                .orElse(SortDirection.ASC);
    }
}
