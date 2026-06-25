package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
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
    private final UserAuthEntityMapper authMapper;

    @Override
    public UserAuth findUserById(UUID id) {
        UserAuthEntity entity = users.findById(id).orElseThrow();
        return authMapper.toDomain(entity);
    }

    @Override
    public UserAuth findUserByEmail(String email) {
        UserAuthEntity entity = users.findByEmail(email);
        return authMapper.toDomain(entity);
    }

}
