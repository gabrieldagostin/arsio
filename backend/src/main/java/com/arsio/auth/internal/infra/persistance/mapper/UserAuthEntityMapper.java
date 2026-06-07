package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserAuthEntityMapper {

    UserAuthEntity toEntity(UserAuth userAuth);

    UserAuth toDomain(UserAuthEntity userAuthEntity);
}
