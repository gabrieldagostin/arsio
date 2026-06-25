package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import com.arsio.auth.internal.infra.persistance.mapper.UserSessionEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaSessionAdapter implements SessionRepository {

    private final SpringDataUserSessionRepository sessions;
    private final UserSessionEntityMapper sessionMapper;

    @Override
    public void save(UserSession userSession) {
        UserSessionEntity userSessionEntity = sessionMapper.toEntity(userSession);
        sessions.save(userSessionEntity);
    }

    @Override
    public UserSession findBySessionId(SessionId sessionId) {
        UserSessionEntity userSessionEntity = sessions.findByIdAndRevokedFalse(sessionId.value());
        return sessionMapper.toDomain(userSessionEntity);
    }

}
