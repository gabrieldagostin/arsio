package com.arsio.user.internal.infra.persistence.adapter;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.controller.mapper.PaginationMapper;
import com.arsio.user.internal.infra.persistence.entity.UserEntity;
import com.arsio.user.internal.infra.persistence.mapper.UserEntityMapper;
import com.arsio.user.internal.infra.persistence.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository users;
    private final UserEntityMapper mapper;
    private final PaginationMapper paginationMapper;

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
    public PageResult<User> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<UserEntity> result;

        if (search == null || search.isBlank()) {
            result = users.findAll(pageable);
        } else {
            result = users.findByUsernameContainingIgnoreCase(search.trim(), pageable);
        }

        List<User> userList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                userList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
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
