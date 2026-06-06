package com.arsio.auth.internal.infra.controller;

import com.arsio.auth.internal.application.dto.CreateUserCommand;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.application.service.CreateUserService;
import com.arsio.auth.internal.application.service.LoginUserService;
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

    private final AuthControllerMapper mapper;
    private final CreateUserService createUserService;
    private final LoginUserService loginUserService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request, UriComponentsBuilder builder) {

        CreateUserCommand command = mapper.toCreateUserCommand(request);

        AuthenticationResponse response = createUserService.execute(command);

        URI uri = builder.path("/users/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserRequest request, UriComponentsBuilder builder) {

        LoginUserComand command = mapper.toLoginUserCommand(request);

        AuthenticationResponse response = loginUserService.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {

        RefreshTokenCommand command = mapper.toRefreshTokenCommand(request);

        RefreshTokenResponse response = refreshTokenService.execute(command);

        return ResponseEntity.ok(response);
    }

}
