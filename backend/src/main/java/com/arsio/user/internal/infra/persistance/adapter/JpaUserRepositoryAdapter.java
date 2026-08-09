package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import com.arsio.user.internal.infra.persistance.mapper.UserEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<UserDetails> findUserDetailsByUsername(String username) {
        return users.findUserDetailsByUsername(username);
    }

    @Override
    public Optional<User> findUserByUsername(Username username) {
        return users.findUserByUsername(username.getNormalized())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return users.findById(id.value())
                .map(mapper::toDomain);
    }
}
