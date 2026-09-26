package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.persistence.entity.GpuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GpuEntityMapper {

    Gpu toDomain(GpuEntity gpuEntity);

    @Mapping(target = "createdAt", ignore = true)
    GpuEntity toEntity(Gpu gpu);

    default UUID requirementIdToUuid(RequirementId requirementId) {
        return requirementId.value();
    }

    default RequirementId uuidToRequirementId(UUID value) {
        return new RequirementId(value);
    }

    default String modelToString(Model model) {
        return model.value();
    }

    default Model stringToModel(String value) {
        return new Model(value);
    }

    default String manufacturerToString(Manufacturer manufacturer) {
        return manufacturer.value();
    }

    default Manufacturer stringToManufacturer(String value) {
        return new Manufacturer(value);
    }
}
