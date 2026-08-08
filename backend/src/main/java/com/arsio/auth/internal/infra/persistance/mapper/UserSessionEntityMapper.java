package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserSessionEntityMapper {

    @Mapping(target = "revokedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserSessionEntity toEntity(UserSession userSession);

    UserSession toDomain(UserSessionEntity userSessionEntity);

    default UUID sessionIdToUuid(SessionId sessionId) {
        return sessionId.value();
    }

    default SessionId uuidToSessionId(UUID value) {
        return new SessionId(value);
    }

    default String refreshTokenToString(RefreshToken refreshToken) {
        return refreshToken.value();
    }

    default RefreshToken stringToRefreshToken(String value) {
        return new RefreshToken(value);
    }
}
