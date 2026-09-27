package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.persistence.entity.OperatingSystemEntity;
import com.arsio.game.internal.infra.persistence.mapper.OperatingSystemEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataOperatingSystemRepository;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaOperatingSystemRepositoryAdapter implements OperatingSystemRepository {

    private final OperatingSystemEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataOperatingSystemRepository operatingSystems;

    @Override
    public PageResult<OperatingSystem> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<OperatingSystemEntity> result;

        if (search == null || search.isBlank()) {
            result = operatingSystems.findAllByActiveTrue(pageable);
        } else {
            result = operatingSystems.findAllByNameContainingIgnoreCaseAndActiveTrue(search.trim(), pageable);
        }

        List<OperatingSystem> operatingSystemList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                operatingSystemList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public void save(OperatingSystem operatingSystem) {
        operatingSystems.save(mapper.toEntity(operatingSystem));
    }

    @Override
    public Optional<OperatingSystem> findById(RequirementId operatingSystemId) {
        return operatingSystems.findByIdAndActiveTrue(operatingSystemId.value())
                .map(mapper::toDomain);
    }
}
