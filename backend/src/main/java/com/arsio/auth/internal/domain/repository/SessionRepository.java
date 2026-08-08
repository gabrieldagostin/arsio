package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    void save(UserSession userSession);

    Optional<UserSession> findBySessionId(SessionId sessionId);

    void revokeAllSessionsByUserId(UUID userId);
}
