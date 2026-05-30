package com.arsio.auth.internal.application.port.input;

import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.infra.controller.dto.LoginUserComand;

public interface LoginUserUseCase {
    AuthenticationResponse execute(LoginUserComand comand);
}
