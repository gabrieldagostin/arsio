package com.arsio.developer.internal.infra.persistance.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.developer.internal.domain.model.Developer;
import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.domain.valueobject.MpAccessToken;
import com.arsio.developer.internal.infra.persistance.entity.DeveloperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface DeveloperEntityMapper {

    Developer toDomain(DeveloperEntity developerEntity);

    @Mapping(target = "lastLoginAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DeveloperEntity toEntity(Developer developer);

    default UUID developerIdToUuid(DeveloperId developerId) {
        return developerId.value();
    }

    default DeveloperId uuidToDeveloperId(UUID value) {
        return new DeveloperId(value);
    }

    default String mpAccessTokenToString(MpAccessToken mpAccessToken) {
        return mpAccessToken.value();
    }

    default MpAccessToken stringToMpAccessToken(String value) {
        return new MpAccessToken(value);
    }
}
