package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.shared.valueobject.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RevokeAllSessionsService {

    private final SessionRepository sessions;

    public RevokeAllSessionsService(SessionRepository sessions) {
        this.sessions = sessions;
    }

    public void execute(UserId userId) {
        sessions.revokeAllSessionsByUserId(userId);
    }
}
