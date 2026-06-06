package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.UserSession;

import java.util.UUID;

public interface SessionRepository {

    void save(UserSession userSession);

    UserSession findBySessionId(UUID sessionId);
}
