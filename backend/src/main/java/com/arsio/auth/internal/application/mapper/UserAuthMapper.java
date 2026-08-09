package com.arsio.auth.internal.application.mapper;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.api.dto.UserCreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserAuthMapper {

    UserAuth toDomain(UserCreatedResponse userCreatedResponse);

    UserCreatedResponse toResponse(UserAuth userAuth);
}
