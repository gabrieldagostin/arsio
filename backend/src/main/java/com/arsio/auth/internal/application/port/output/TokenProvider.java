package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;

import java.util.UUID;

public interface TokenProvider {

    AccessToken generateAccessToken(UserAuth user);

    RefreshToken generateRefreshToken(UserAuth user, SessionId sessionId);

    UUID extractSubject(String token);

    SessionId extractSessionId(String token);
}
