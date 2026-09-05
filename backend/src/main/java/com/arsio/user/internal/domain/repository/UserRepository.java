package com.arsio.user.internal.domain.repository;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);

    boolean existsByEmail(Email email);

    boolean existsByUsername(Username username);

    Optional<UserAuthenticationData> findUserAuthenticationDataById(UUID id);

    Optional<User> findById(UserId id);

    Page<User> findAll(String search, Pageable pageable);

    Optional<UserAuthenticationData> findUserAuthenticationDataByUsername(String username);
}
