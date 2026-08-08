package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.valueobject.PasswordResetTokenId;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.infra.persistance.entity.PasswordResetTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface PasswordResetTokenEntityMapper {

    PasswordResetToken toDomain(PasswordResetTokenEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    PasswordResetTokenEntity toEntity(PasswordResetToken domain);

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
