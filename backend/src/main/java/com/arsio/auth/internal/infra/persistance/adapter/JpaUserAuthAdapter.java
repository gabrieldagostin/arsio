package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistance.mapper.UserAuthEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserAuthRepository;
import com.arsio.shared.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserAuthAdapter implements UserAuthRepository {

    private final SpringDataUserAuthRepository users;
    private final UserAuthEntityMapper mapper;

    @Override
    public Optional<UserAuth> findUserById(UserId id) {
        return users.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserAuth> findUserByEmail(String email) {
        return users.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserAuth> findAuthenticationDataByUsername(String username) {
        return users.findByUsernameNormalized(username)
                .map(mapper::toDomain);
    }
}
