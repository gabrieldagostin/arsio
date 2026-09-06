package com.arsio.auth.internal.application.usecase;

import com.arsio.auth.internal.application.command.LogoutCommand;
import com.arsio.auth.internal.domain.exception.SessionNotFoundException;
import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutUserUseCase {

    private final SessionRepository sessions;
    private final TokenProvider tokenProvider;

    public LogoutUserUseCase(SessionRepository sessions, TokenProvider tokenProvider) {
        this.sessions = sessions;
        this.tokenProvider = tokenProvider;
    }

    public void execute(LogoutCommand command) {

        SessionId sessionId = tokenProvider.extractSessionId(command.refreshToken().value());

        UserSession userSession = sessions.findBySessionId(sessionId)
                .orElseThrow(SessionNotFoundException::new);

        userSession.logout();

        sessions.save(userSession);
    }
}
