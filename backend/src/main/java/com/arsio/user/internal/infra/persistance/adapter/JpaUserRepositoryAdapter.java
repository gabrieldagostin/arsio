package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import com.arsio.user.internal.infra.persistance.mapper.UserEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository users;
    private final UserEntityMapper mapper;

    @Override
    public void save(User user) {
        UserEntity entity = mapper.toEntity(user);
        users.save(entity);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return users.existsByEmail(
                email.value()
        );
    }

    @Override
    public boolean existsByUsername(Username username) {
        return users.existsByUsername(
                username.getNormalized()
        );
    }

    @Override
    public Optional<UserAuthenticationData> findUserAuthenticationDataById(UUID id) {
        return users.findUserAuthenticationDataById(id)
                .map(user -> new UserAuthenticationData(
                        user.id(),
                        user.username(),
                        user.email(),
                        user.passwordHash(),
                        user.role()
                ));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return users.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Page<User> findAll(String search, Pageable pageable) {

        if (search == null || search.isBlank()) {
            return users.findAll(pageable)
                    .map(mapper::toDomain);
        }

        return users.findByUsernameContainingIgnoreCase(search.trim(), pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserAuthenticationData> findUserAuthenticationDataByUsername(String username) {
        return users.findUserAuthenticationDataByUsername(username)
                .map(user -> new UserAuthenticationData(
                        user.id(),
                        user.username(),
                        user.email(),
                        user.passwordHash(),
                        user.role()
                ));
    }
}
