package com.arsio.user.internal.infra.persistance.mapper;

import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Password;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "mpAccessToken", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
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
        return password.hashedValue();
    }

    default Password stringToUserPassword(String value) {
        return new Password(value);
    }

}
