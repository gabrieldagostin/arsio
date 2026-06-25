package com.arsio.user.internal.application.facade;

import com.arsio.user.api.dto.CreateUserCommand;
import com.arsio.user.api.dto.UpdateUserPasswordHashCommand;
import com.arsio.user.api.dto.UserCreatedResponse;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.user.internal.application.service.UpdateUserPasswordHashService;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.application.service.CreateUserService;
import com.arsio.user.internal.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserRepository users;
    private final CreateUserService createUserService;
    private final UpdateUserPasswordHashService updateUserPasswordService;

    public UserFacadeImpl(UserRepository users, CreateUserService createUserService, UpdateUserPasswordHashService updateUserPasswordService) {
        this.users = users;
        this.createUserService = createUserService;
        this.updateUserPasswordService = updateUserPasswordService;
    }

    @Override
    public UserCreatedResponse createUser(String username, String email, String password) {

        User user = createUserService.execute(
                new CreateUserCommand(username, email, password)
        );

        return new UserCreatedResponse(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                user.getPassword().hashedValue(),
                user.getRole().name()
        );
    }

    @Override
    public UUID findUserByUsernameNormalized(String username) {
        User user = users.findUserByUsernameNormalized(username);
        return user.getId().value();
    }

    @Override
    public UserDetails findUserDetailsByUsernameNormalized(String username) {
        return users.findUserDetailsByUsernameNormalized(username);
    }

    @Override
    public void updateUserPasswordHash(UUID id, String passwordHash) {

        updateUserPasswordService.execute(
                new UpdateUserPasswordHashCommand(
                        id,
                        passwordHash
                )
        );
    }
}
