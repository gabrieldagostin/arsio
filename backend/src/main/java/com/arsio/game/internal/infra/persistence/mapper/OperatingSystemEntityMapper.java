package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.OperatingSystem;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.persistence.entity.OperatingSystemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface OperatingSystemEntityMapper {

    OperatingSystem toDomain(OperatingSystemEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    OperatingSystemEntity toEntity(OperatingSystem domain);

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
}
