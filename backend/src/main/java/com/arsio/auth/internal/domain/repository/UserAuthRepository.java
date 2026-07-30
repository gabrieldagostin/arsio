package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.shared.valueobject.UserId;

import java.util.Optional;

public interface UserAuthRepository {

    Optional<UserAuth> findUserById(UserId id);

    Optional<UserAuth> findUserByEmail(String email);

    Optional<UserAuth> findAuthenticationDataByUsername(String username);
}
