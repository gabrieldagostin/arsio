package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.infra.persistence.mapper.GameMediaEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGameMediaRepositoryAdapter implements GameMediaRepository {

    private final GameMediaEntityMapper mapper;
    private final SpringDataGameMediaRepository gameMedia;
}
