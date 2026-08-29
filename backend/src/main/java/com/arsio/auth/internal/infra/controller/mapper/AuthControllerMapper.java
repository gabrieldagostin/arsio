package com.arsio.auth.internal.infra.controller.mapper;

import com.arsio.auth.internal.application.command.*;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.infra.controller.dto.request.*;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface AuthControllerMapper {

     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);

     LoginUserComand toLoginUserCommand(LoginUserRequest request);

     RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);

     LogoutCommand toLogoutCommand(LogoutRequest request);

     ForgotPasswordCommand toForgotPasswordCommand(ForgotPasswordRequest request);

     ResetPasswordCommand toResetPasswordCommand(ResetPasswordRequest request);

     default RefreshToken stringToRefreshToken(String refreshToken) {
          return new RefreshToken(refreshToken);
     }

     default PasswordToken  stringToPasswordToken(String passwordToken) {
          return new PasswordToken(passwordToken);
     }
}
