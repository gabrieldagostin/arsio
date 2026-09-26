package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.infra.persistence.entity.GpuEntity;
import com.arsio.game.internal.infra.persistence.mapper.GpuEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGpuRepository;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGpuRepositoryAdapter implements GpuRepository {

    private final GpuEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataGpuRepository gpus;

    @Override
    public PageResult<Gpu> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<GpuEntity> result;

        if (search == null || search.isBlank()) {
            result = gpus.findAll(pageable);
        } else {
            result = gpus.findByModelContainingIgnoreCaseAndActiveTrue(search.trim(), pageable);
        }

        List<Gpu> gpuList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                gpuList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
