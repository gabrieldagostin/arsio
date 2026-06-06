package com.arsio.auth.internal.application.port.output;

import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;


public interface TokenProvider {

    AccessToken generateAccessToken(User user);

    RefreshToken generateRefreshToken(User user, SessionId sessionId);

    String extractSubject(String token);

    SessionId extractSessionId(String token);
}
