package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.application.port.output.UserRepository;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import com.arsio.user.internal.infra.persistance.mapper.UserEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataUserRepository;
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
                email.value()
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
        UserEntity entity = repository.findUserByUsernameNormalized(
                new Username(username).getNormalized());
        return mapper.toDomain(entity);
    }


}
