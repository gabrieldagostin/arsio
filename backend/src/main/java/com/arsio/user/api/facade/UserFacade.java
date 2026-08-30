package com.arsio.user.api.facade;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.api.dto.UserCreatedResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserFacade {

    UserCreatedResponse createUser(String username, String email, String password);

    void updateUserPasswordHash(UUID id, String passwordHash);

    Optional<UserAuthenticationData> findUserAuthenticationDataByUsername(String username);
}
