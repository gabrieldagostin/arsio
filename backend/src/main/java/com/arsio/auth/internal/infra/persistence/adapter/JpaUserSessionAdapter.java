package com.arsio.auth.internal.infra.persistence.adapter;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import com.arsio.auth.internal.infra.persistence.entity.UserSessionEntity;
import com.arsio.auth.internal.infra.persistence.mapper.UserSessionEntityMapper;
import com.arsio.auth.internal.infra.persistence.repository.SpringDataUserSessionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserSessionAdapter implements SessionRepository {

    private final SpringDataUserSessionRepository sessions;
    private final UserSessionEntityMapper mapper;
    private final EntityManager entityManager;

    @Override
    public void save(UserSession userSession) {

        UserAuthEntity userAuthEntity = entityManager.getReference(
                UserAuthEntity.class,
                userSession.getUserId()
        );

        UserSessionEntity userSessionEntity = mapper.toEntity(userSession,  userAuthEntity);

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
