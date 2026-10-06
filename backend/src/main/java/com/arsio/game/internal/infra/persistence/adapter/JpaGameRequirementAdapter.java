package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.domain.repository.GameRequirementRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;
import com.arsio.game.internal.infra.persistence.entity.*;
import com.arsio.game.internal.infra.persistence.mapper.GameRequirementEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameRequirementRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGameRequirementAdapter implements GameRequirementRepository {

    private final GameRequirementEntityMapper mapper;
    private final SpringDataGameRequirementRepository gameRequirements;
    private final EntityManager entityManager;

    @Override
    public GetGameRequirementResponse save(GameRequirement gameRequirement) {

        GameEntity gameEntity = entityManager.getReference(
                GameEntity.class,
                gameRequirement.getGameId().value()
        );

        OperatingSystemEntity operatingSystemEntity = entityManager.getReference(
                OperatingSystemEntity.class,
                gameRequirement.getOperatingSystemId().value()
        );

        ProcessorEntity processorEntity = entityManager.getReference(
                ProcessorEntity.class,
                gameRequirement.getProcessorId().value()
        );

        GpuEntity gpuEntity = entityManager.getReference(
                GpuEntity.class,
                gameRequirement.getGpuId().value()
        );

        gameRequirements.save(mapper.toEntity(
                gameRequirement,
                gameEntity,
                operatingSystemEntity,
                processorEntity,
                gpuEntity
        ));

        return new GetGameRequirementResponse(
                gameRequirement.getId().value(),
                gameRequirement.getGameId().value(),
                gameRequirement.getCategory().name(),
                operatingSystemEntity.getName(),
                processorEntity.getManufacturer(),
                processorEntity.getModel(),
                gpuEntity.getManufacturer(),
                gpuEntity.getModel(),
                gameRequirement.getRam().value(),
                gameRequirement.getStorage().value(),
                gameRequirement.getStorageUnit().name()
        );
    }

    @Override
    public GetGameRequirementResponse findByGameIdAndCategoryMinimum(GameId gameId) {

        GameRequirement gameRequirement = mapper.toDomain(
                gameRequirements.findByGameIdAndCategory(gameId.value(), RequirementCategory.MINIMUM)
        );

        OperatingSystemEntity operatingSystemEntity = entityManager.getReference(
                OperatingSystemEntity.class,
                gameRequirement.getOperatingSystemId().value()
        );

        ProcessorEntity processorEntity = entityManager.getReference(
                ProcessorEntity.class,
                gameRequirement.getProcessorId().value()
        );

        GpuEntity gpuEntity = entityManager.getReference(
                GpuEntity.class,
                gameRequirement.getGpuId().value()
        );

        return new GetGameRequirementResponse(
                gameRequirement.getId().value(),
                gameRequirement.getGameId().value(),
                gameRequirement.getCategory().name(),
                operatingSystemEntity.getName(),
                processorEntity.getManufacturer(),
                processorEntity.getModel(),
                gpuEntity.getManufacturer(),
                gpuEntity.getModel(),
                gameRequirement.getRam().value(),
                gameRequirement.getStorage().value(),
                gameRequirement.getStorageUnit().name()
        );
    }

    @Override
    public GetGameRequirementResponse findByGameIdAndCategoryRecommended(GameId gameId) {

        GameRequirement gameRequirement = mapper.toDomain(
                gameRequirements.findByGameIdAndCategory(gameId.value(), RequirementCategory.RECOMMENDED)
        );

        OperatingSystemEntity operatingSystemEntity = entityManager.getReference(
                OperatingSystemEntity.class,
                gameRequirement.getOperatingSystemId().value()
        );

        ProcessorEntity processorEntity = entityManager.getReference(
                ProcessorEntity.class,
                gameRequirement.getProcessorId().value()
        );

        GpuEntity gpuEntity = entityManager.getReference(
                GpuEntity.class,
                gameRequirement.getGpuId().value()
        );

        return new GetGameRequirementResponse(
                gameRequirement.getId().value(),
                gameRequirement.getGameId().value(),
                gameRequirement.getCategory().name(),
                operatingSystemEntity.getName(),
                processorEntity.getManufacturer(),
                processorEntity.getModel(),
                gpuEntity.getManufacturer(),
                gpuEntity.getModel(),
                gameRequirement.getRam().value(),
                gameRequirement.getStorage().value(),
                gameRequirement.getStorageUnit().name()
        );
    }
}
