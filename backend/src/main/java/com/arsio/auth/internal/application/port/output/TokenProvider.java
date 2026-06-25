package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;

import java.util.Optional;


public interface TokenProvider {

    AccessToken generateAccessToken(UserAuth user);

    RefreshToken generateRefreshToken(UserAuth user, SessionId sessionId);

    String extractSubject(String token);

    SessionId extractSessionId(String token);
}
