package com.arsio.auth.internal.infra.controller;

import com.arsio.auth.internal.application.dto.CreateUserCommand;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import com.arsio.auth.internal.application.dto.LogoutCommand;
import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.application.service.CreateSessionService;
import com.arsio.auth.internal.application.service.LoginUserService;
import com.arsio.auth.internal.application.service.LogoutUserService;
import com.arsio.auth.internal.application.service.RefreshTokenService;
import com.arsio.auth.internal.infra.controller.dto.*;
import com.arsio.auth.internal.infra.controller.mapper.AuthControllerMapper;
import com.arsio.config.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequestMapping("/auth")
public class AuthController {

    private final AuthControllerMapper authControllerMapper;
    private final CreateSessionService createSessionService;
    private final LoginUserService loginUserService;
    private final RefreshTokenService refreshTokenService;
    private final LogoutUserService logoutUserService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request, UriComponentsBuilder builder) {

        CreateUserCommand command = authControllerMapper.toCreateUserCommand(request);

        AuthenticationResponse response = createSessionService.execute(command);

        URI uri = builder.path("/users/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserRequest request, UriComponentsBuilder builder) {

        LoginUserComand command = authControllerMapper.toLoginUserCommand(request);

        AuthenticationResponse response = loginUserService.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {

        RefreshTokenCommand command = authControllerMapper.toRefreshTokenCommand(request);

        RefreshTokenResponse response = refreshTokenService.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid LogoutRequest request) {

        LogoutCommand command = authControllerMapper.toLogoutCommand(request);

        logoutUserService.execute(command);

        return ResponseEntity.noContent().build();
    }
}
