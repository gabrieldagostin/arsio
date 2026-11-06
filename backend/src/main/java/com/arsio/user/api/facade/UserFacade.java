package com.arsio.user.api.facade;

import com.arsio.user.api.dto.GetDeveloperProfileView;
import com.arsio.user.api.dto.GetUserSummaryView;
import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.api.dto.UserCreatedResponse;

import java.util.Optional;
import java.util.UUID;

public interface UserFacade {

    UserCreatedResponse createUser(String username, String email, String password);

    void updateUserPasswordHash(UUID id, String passwordHash);

    Optional<UserAuthenticationData> findUserAuthenticationDataById(UUID id);

    Optional<UserAuthenticationData> findUserAuthenticationDataByUsername(String username);

    Optional<GetDeveloperProfileView> findById(UUID developerId);

    Optional<GetUserSummaryView> findAuthorInfo(UUID userId);
}
