package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.GameRequirementRepository;
import com.arsio.game.internal.infra.persistence.mapper.GameRequirementEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameRequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGameRequirementAdapter implements GameRequirementRepository {

    private final GameRequirementEntityMapper mapper;
    private final SpringDataGameRequirementRepository gameRequirements;
}
