package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.valueobject.PasswordResetTokenId;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.infra.persistance.entity.PasswordResetTokenEntity;
import com.arsio.auth.internal.infra.persistance.entity.UserAuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface PasswordResetTokenEntityMapper {

    @Mapping(target = "id", source = "passwordResetTokenEntity.id")
    @Mapping(target = "userId", source = "passwordResetTokenEntity.user.id")
    PasswordResetToken toDomain(PasswordResetTokenEntity passwordResetTokenEntity);

    @Mapping(target = "id", source = "passwordResetToken.id")
    @Mapping(target = "user", source = "userAuthEntity")
    @Mapping(target = "createdAt", ignore = true)
    PasswordResetTokenEntity toEntity(PasswordResetToken passwordResetToken, UserAuthEntity userAuthEntity);

    default UUID passwordResetTokenIdToUuid(PasswordResetTokenId passwordResetTokenId) {
        return passwordResetTokenId.value();
    }

    default PasswordResetTokenId uuidToPasswordResetTokenId(UUID value) {
        return new PasswordResetTokenId(value);
    }

    default String passwordTokenToString(PasswordToken passwordToken) {
        return passwordToken.value();
    }

    default PasswordToken stringToPasswordToken(String value) {
        return new PasswordToken(value);
    }
}
