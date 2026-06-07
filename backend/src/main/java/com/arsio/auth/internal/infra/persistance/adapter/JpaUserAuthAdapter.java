package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.application.port.output.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import com.arsio.auth.internal.infra.persistance.mapper.UserAuthEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserAuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaUserAuthAdapter implements UserAuthRepository {

    private final SpringDataUserAuthRepository users;
    private final UserAuthEntityMapper authMapper;

    @Override
    public Optional<UserAuth> findUserById(UUID id) {
        Optional<UserAuthEntity> userAuthEntity = users.findById(id);
        return userAuthEntity.map(authMapper::toDomain);
    }

}
