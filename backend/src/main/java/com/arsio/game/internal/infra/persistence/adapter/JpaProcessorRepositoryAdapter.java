package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.persistence.entity.ProcessorEntity;
import com.arsio.game.internal.infra.persistence.mapper.ProcessorEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataProcessorRepository;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProcessorRepositoryAdapter implements ProcessorRepository {

    private final ProcessorEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataProcessorRepository processors;

    @Override
    public PageResult<Processor> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<ProcessorEntity> result;

        if (search == null || search.isBlank()) {
            result = processors.findAll(pageable);
        } else {
            result = processors.findByModelContainingIgnoreCaseAndActiveTrue(search.trim(), pageable);
        }

        List<Processor> processorList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                processorList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public void save(Processor processor) {
        processors.save(mapper.toEntity(processor));
    }

    @Override
    public Optional<Processor> findById(RequirementId processorId) {
        return processors.findById(processorId.value())
                .map(mapper::toDomain);
    }
}
