package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserAuth;

import java.util.UUID;

public interface UserAuthRepository {

    UserAuth findUserById(UUID id);

    UserAuth findUserByEmail(String email);

}
