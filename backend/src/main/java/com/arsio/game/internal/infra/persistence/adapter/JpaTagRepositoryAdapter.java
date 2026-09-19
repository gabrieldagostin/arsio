package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.infra.persistence.entity.TagEntity;
import com.arsio.game.internal.infra.persistence.mapper.TagEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataTagRepository;
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
public class JpaTagRepositoryAdapter implements TagRepository {

    private final TagEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataTagRepository tags;

    @Override
    public PageResult<Tag> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<TagEntity> result;

        if (search == null || search.isBlank()) {
            result = tags.findAll(pageable);
        } else {
            result = tags.findByNameContainingIgnoreCaseAndActiveTrue(search.trim(), pageable);
        }

        List<Tag> tagList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                tagList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
