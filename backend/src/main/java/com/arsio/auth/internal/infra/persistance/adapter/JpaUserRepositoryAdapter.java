package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.application.port.output.UserRepository;
import com.arsio.auth.internal.domain.valueobject.Email;
import com.arsio.auth.internal.domain.valueobject.Username;
import com.arsio.auth.internal.infra.persistance.entity.UserEntity;
import com.arsio.auth.internal.infra.persistance.mapper.UserEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public void save(User user) {
        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(
                email.getValue()
        );
    }

    @Override
    public boolean existsByUsernameNormalized(Username username) {
        return repository.existsByUsernameNormalized(
                username.getNormalized()
        );
    }

    @Override
    public UserDetails findUserDetailsByUsernameNormalized(String username) {
        return repository.findUserDetailsByUsernameNormalized(
                new Username(username).getNormalized()
        );
    }

    @Override
    public User findUserByUsernameNormalized(String username) {
        UserEntity entity = repository.findUserByUsernameNormalized(username);
        return mapper.toDomain(entity);
    }


}
