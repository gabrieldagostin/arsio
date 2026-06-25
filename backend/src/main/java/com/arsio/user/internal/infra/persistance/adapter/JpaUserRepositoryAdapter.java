package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import com.arsio.user.internal.infra.persistance.mapper.UserEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository users;
    private final UserEntityMapper userMapper;

    @Override
    public void save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        users.save(entity);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return users.existsByEmail(
                email.value()
        );
    }

    @Override
    public boolean existsByUsernameNormalized(Username username) {
        return users.existsByUsernameNormalized(
                username.getNormalized()
        );
    }

    @Override
    public UserDetails findUserDetailsByUsernameNormalized(String username) {
        return users.findUserDetailsByUsernameNormalized(
                new Username(username).getNormalized()
        );
    }

    @Override
    public User findUserByUsernameNormalized(String username) {
        UserEntity entity = users.findUserByUsernameNormalized(
                new Username(username).getNormalized());
        return userMapper.toDomain(entity);
    }

    @Override
    public User findById(UUID id) {
        UserEntity entity = users.findById(id).orElseThrow();
        return userMapper.toDomain(entity);
    }


}
