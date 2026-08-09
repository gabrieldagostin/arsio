package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistance.mapper.UserAuthEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserAuthAdapter implements UserAuthRepository {

    private final SpringDataUserAuthRepository users;
    private final UserAuthEntityMapper mapper;

    @Override
    public Optional<UserAuth> findUserById(UUID id) {
        return users.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserAuth> findUserByEmail(String email) {
        return users.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserAuth> findAuthenticationDataByUsername(String username) {
        return users.findByUsername(username)
                .map(mapper::toDomain);
    }
}
