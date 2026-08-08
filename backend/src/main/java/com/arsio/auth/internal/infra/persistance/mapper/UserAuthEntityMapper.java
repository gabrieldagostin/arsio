package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserAuthEntityMapper {

    @Mapping(target = "role", ignore = true)
    UserAuthEntity toEntity(UserAuth userAuth);

    UserAuth toDomain(UserAuthEntity userAuthEntity);
}
