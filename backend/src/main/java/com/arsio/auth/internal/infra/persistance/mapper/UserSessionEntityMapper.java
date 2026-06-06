package com.arsio.auth.internal.infra.persistance.mapper;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.domain.valueobject.UserId;
import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserSessionEntityMapper {

    UserSessionEntity toEntity(UserSession userSession);

    UserSession toDomain(UserSessionEntity userSessionEntity);

    default UUID sessionIdToUuid(SessionId sessionId) {
        return sessionId.value();
    }

    default SessionId uuidToSessionId(UUID value) {
        return new SessionId(value);
    }

    default UUID userIdToUuid(UserId userId) {
        return userId.value();
    }

    default UserId uuidToUserId(UUID value) {
        return new UserId(value);
    }

    default String refreshTokenToString(RefreshToken refreshToken) {
        return refreshToken.value();
    }

    default RefreshToken stringToRefreshToken(String value) {
        return new RefreshToken(value);
    }

}
