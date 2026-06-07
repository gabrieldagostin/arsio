package com.arsio.auth.internal.application.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.user.api.dto.UserCreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserAuthMapper {
    UserAuth toDomain(UserCreatedResponse userCreatedResponse);
    UserCreatedResponse toResponse(UserAuth userAuth);
}
