package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.valueobject.Email;
import com.arsio.auth.internal.domain.valueobject.Username;
import com.arsio.auth.internal.infra.persistance.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {

    void save(User user);

    boolean existsByEmail(Email email);

    boolean existsByUsernameNormalized(Username username);

    UserDetails findUserDetailsByUsernameNormalized(String username);

    User findUserByUsernameNormalized(String username);

}
