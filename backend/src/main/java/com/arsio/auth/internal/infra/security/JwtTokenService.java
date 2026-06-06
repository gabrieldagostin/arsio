package com.arsio.auth.internal.infra.security;

import com.arsio.auth.api.facade.AuthFacade;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenProvider, AuthFacade {

    private final TokenProperties tokenProperties;

    @Override
    public AccessToken generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            String token = JWT.create()
                    .withIssuer("arsio")
                    .withSubject(user.getUsername().value())
                    .withExpiresAt(genExpirationDate(tokenProperties.getAccessTokenExpiration()))
                    .sign(algorithm);
            return new AccessToken(token);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    @Override
    public RefreshToken generateRefreshToken(User user, SessionId sessionId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            String token = JWT.create()
                    .withIssuer("arsio")
                    .withSubject(user.getUsername().value())
                    .withClaim("session_id", sessionId.value().toString())
                    .withExpiresAt(genExpirationDate(tokenProperties.getRefreshTokenExpiration()))
                    .sign(algorithm);
            return new RefreshToken(token);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating refresh token", exception);
        }
    }

    @Override
    public String extractSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Error wihile extracting subject", exception);
        }
    }

    @Override
    public SessionId extractSessionId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            UUID value = UUID.fromString(JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("session_id").asString());
            return new SessionId(value);
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Error while extracting session_id", exception);
        }
    }

    private Instant genExpirationDate(Duration expiration) {
        Long expiresAt = expiration.toHours();
        return LocalDateTime.now().plusHours(expiresAt).toInstant(ZoneOffset.of("-03:00"));
    }
}
