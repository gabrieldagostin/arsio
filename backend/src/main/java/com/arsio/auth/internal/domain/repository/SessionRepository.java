package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;

public interface SessionRepository {

    void save(UserSession userSession);

    UserSession findBySessionId(SessionId sessionId);

}
