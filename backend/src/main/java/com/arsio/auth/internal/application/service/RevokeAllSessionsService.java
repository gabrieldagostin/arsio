package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class RevokeAllSessionsService {

    private final SessionRepository sessions;

    public RevokeAllSessionsService(SessionRepository sessions) {
        this.sessions = sessions;
    }

    public void execute(UUID userId) {
        sessions.revokeAllSessionsByUserId(userId);
    }
}
