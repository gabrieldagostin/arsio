package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;

import java.util.UUID;

public interface SessionRepository {

    void save(UserSession userSession);

    UserSession findBySessionId(SessionId sessionId);

}
