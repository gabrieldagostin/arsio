package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserAuth;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthRepository {

    Optional<UserAuth> findUserById(UUID id);

    Optional<UserAuth> findUserByEmail(String email);

    Optional<UserAuth> findAuthenticationDataByUsername(String username);
}
