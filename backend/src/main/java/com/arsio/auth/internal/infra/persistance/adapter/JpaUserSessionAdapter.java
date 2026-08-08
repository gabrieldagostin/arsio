package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import com.arsio.auth.internal.infra.persistance.mapper.UserSessionEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserSessionRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserSessionAdapter implements SessionRepository {

    private final SpringDataUserSessionRepository sessions;
    private final UserSessionEntityMapper mapper;

    @Override
    public void save(UserSession userSession) {
        UserSessionEntity userSessionEntity = mapper.toEntity(userSession);
        sessions.save(userSessionEntity);
    }

    @Override
    public Optional<UserSession> findBySessionId(SessionId sessionId) {
        return sessions.findByIdAndRevokedFalse(sessionId.value())
                .map(mapper::toDomain);
    }

    @Override
    public void revokeAllSessionsByUserId(UUID userId) {
        sessions.revokeAllSessionsByUserId(userId);
    }
}
