package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.LogoutCommand;
import com.arsio.auth.internal.application.port.output.SessionRepository;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import org.springframework.stereotype.Service;

@Service
public class LogoutUserService {

    private final SessionRepository sessions;
    private final TokenProvider tokenProvider;

    public LogoutUserService(SessionRepository sessions, TokenProvider tokenProvider) {
        this.sessions = sessions;
        this.tokenProvider = tokenProvider;
    }

    public void execute(LogoutCommand command) {

        SessionId sessionId = tokenProvider.extractSessionId(command.refreshToken());

        UserSession userSession = sessions.findBySessionId(sessionId);

        userSession.logout();

        sessions.save(userSession);
    }
}
