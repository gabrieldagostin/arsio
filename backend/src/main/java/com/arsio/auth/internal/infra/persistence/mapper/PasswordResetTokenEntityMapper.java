package com.arsio.auth.internal.infra.persistence.mapper;

import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.valueobject.PasswordResetTokenId;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.infra.persistence.entity.PasswordResetTokenEntity;
import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
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
