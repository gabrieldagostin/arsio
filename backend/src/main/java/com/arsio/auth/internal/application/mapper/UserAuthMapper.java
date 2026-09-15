package com.arsio.auth.internal.application.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.user.api.dto.UserCreatedResponse;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface UserAuthMapper {

    UserAuth toDomain(UserCreatedResponse userCreatedResponse);

    UserCreatedResponse toResponse(UserAuth userAuth);
}
