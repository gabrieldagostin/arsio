package com.arsio.auth.internal.application.usecase;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class RevokeAllSessionsUseCase {

    private final SessionRepository sessions;

    public RevokeAllSessionsUseCase(SessionRepository sessions) {
        this.sessions = sessions;
    }

    public void execute(UUID userId) {
        sessions.revokeAllSessionsByUserId(userId);
    }
}
