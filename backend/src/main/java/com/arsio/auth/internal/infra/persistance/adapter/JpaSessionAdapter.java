package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.application.port.output.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.infra.persistance.entity.UserSessionEntity;
import com.arsio.auth.internal.infra.persistance.mapper.UserSessionEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataUserSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
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
    public UserSession findBySessionId(UUID sessionId) {
        UserSessionEntity userSessionEntity = sessions.findBySessionId(sessionId);
        return sessionMapper.toDomain(userSessionEntity);
    }

}
