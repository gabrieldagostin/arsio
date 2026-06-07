package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.UserAuth;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthRepository {

    Optional<UserAuth> findUserById(UUID id);
}
