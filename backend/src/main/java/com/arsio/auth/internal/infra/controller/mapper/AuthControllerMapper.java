package com.arsio.auth.internal.infra.controller.mapper;

import com.arsio.auth.internal.application.command.*;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.infra.controller.dto.request.*;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface AuthControllerMapper {

     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);

     LoginUserCommand toLoginUserCommand(LoginUserRequest request);

     RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);

     LogoutCommand toLogoutCommand(LogoutRequest request);

     ForgotPasswordCommand toForgotPasswordCommand(ForgotPasswordRequest request);

     ResetPasswordCommand toResetPasswordCommand(ResetPasswordRequest request);

     default RefreshToken stringToRefreshToken(String value) {
          return new RefreshToken(value);
     }

     default PasswordToken  stringToPasswordToken(String value) {
          return new PasswordToken(value);
     }
}
