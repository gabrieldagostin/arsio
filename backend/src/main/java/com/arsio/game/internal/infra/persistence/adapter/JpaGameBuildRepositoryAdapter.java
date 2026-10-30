package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.GameBuildRepository;
import com.arsio.game.internal.infra.persistence.mapper.GameBuildEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGameBuildRepositoryAdapter implements GameBuildRepository {

    private final GameBuildEntityMapper mapper;
    private final GameBuildRepository gameBuilds;
}
