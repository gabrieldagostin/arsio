package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.model.UserRole;
import com.arsio.auth.internal.domain.valueobject.Email;
import com.arsio.auth.internal.domain.valueobject.Password;
import com.arsio.auth.internal.domain.valueobject.UserId;
import com.arsio.auth.internal.domain.valueobject.Username;
import com.arsio.auth.internal.infra.persistance.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "passwordHash", source = "password")
    UserEntity toEntity(User user);

    @Mapping(target = "password", source = "passwordHash")
    User toDomain(UserEntity userEntity);

    default UUID userIdToUuid(UserId userId) {
        return userId.value();
    }

    default UserId uuidToUserId(UUID value) {
        return new UserId(value);
    }

    default String userUsernameToString(Username username) {
        return username.value();
    }

    default Username stringToUserUsername(String value) {
        return new Username(value);
    }

    default String userEmailToString(Email email) {
        return email.value();
    }

    default Email stringToUserEmail(String value) {
        return new Email(value);
    }

    default String userPasswordToString(Password password) {
        return password.getHashedValue();
    }

    default Password stringToUserPassword(String value) {
        return new Password(value);
    }

}
