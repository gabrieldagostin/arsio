package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.infra.persistence.mapper.TagEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaTagRepositoryAdapter implements TagRepository {

    private final TagEntityMapper mapper;
    private final SpringDataTagRepository tags;
}
