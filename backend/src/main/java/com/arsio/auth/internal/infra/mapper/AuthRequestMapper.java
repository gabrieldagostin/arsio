package com.arsio.auth.internal.infra.mapper;

import com.arsio.auth.internal.infra.controller.dto.CreateUserCommand;
import com.arsio.auth.internal.infra.controller.dto.LoginUserComand;
import com.arsio.auth.internal.infra.controller.dto.LoginUserRequest;
import com.arsio.auth.internal.infra.controller.dto.RegisterUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRequestMapper {
     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);
     LoginUserComand toLoginUserCommand(LoginUserRequest request);
}
