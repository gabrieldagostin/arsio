package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository {

    void save(User user);

    boolean existsByEmail(Email email);

    boolean existsByUsernameNormalized(Username username);

    UserDetails findUserDetailsByUsernameNormalized(String username);

    User findUserByUsernameNormalized(String username);

    User findById(UUID id);
}
