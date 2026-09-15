package com.arsio.auth.internal.infra.persistence.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config =  CentralMapperConfig.class)
public interface UserAuthEntityMapper {

    @Mapping(target = "role", ignore = true)
    UserAuthEntity toEntity(UserAuth userAuth);

    UserAuth toDomain(UserAuthEntity userAuthEntity);
}
