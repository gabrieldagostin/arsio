package com.arsio.user.api.facade;

import com.arsio.user.api.dto.UserCreatedResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserFacade {

    UserCreatedResponse createUser(String username, String email, String password);

    Optional<UserDetails> findUserDetailsByUsername(String username);

    void updateUserPasswordHash(UUID id, String passwordHash);
}
