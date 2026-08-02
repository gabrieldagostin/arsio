package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.shared.valueobject.UserId;

import java.util.Optional;

public interface SessionRepository {

    void save(UserSession userSession);

    Optional<UserSession> findBySessionId(SessionId sessionId);

    void revokeAllSessionsByUserId(UserId userId);
}
