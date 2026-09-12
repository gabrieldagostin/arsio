package com.arsio.auth.internal.infra.persistence.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserAuthEntityMapper {

    @Mapping(target = "role", ignore = true)
    UserAuthEntity toEntity(UserAuth userAuth);

    UserAuth toDomain(UserAuthEntity userAuthEntity);
}
