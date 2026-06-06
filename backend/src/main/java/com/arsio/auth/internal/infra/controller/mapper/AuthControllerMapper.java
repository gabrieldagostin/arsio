package com.arsio.auth.internal.infra.controller.mapper;

import com.arsio.auth.internal.application.dto.CreateUserCommand;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.infra.controller.dto.LoginUserRequest;
import com.arsio.auth.internal.infra.controller.dto.RefreshTokenRequest;
import com.arsio.auth.internal.infra.controller.dto.RegisterUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);
     LoginUserComand toLoginUserCommand(LoginUserRequest request);
     RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);
}
