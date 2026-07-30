package com.arsio.user.internal.domain.repository;

import com.arsio.shared.valueobject.UserId;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    boolean existsByEmail(Email email);

    boolean existsByUsername(Username username);

    Optional<UserDetails> findUserDetailsByUsername(String username);

    Optional<User> findUserByUsername(Username username);

    Optional<User> findById(UserId id);
}
