package com.arsio.auth.internal.infra.security;

import com.arsio.auth.api.facade.AuthFacade;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.exception.InvalidTokenException;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.UnknownNullability;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenProvider, AuthFacade {

    private final TokenProperties tokenProperties;

    @Override
    public AccessToken generateAccessToken(UserAuth user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            String token = JWT.create()
                    .withIssuer("arsio")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate(tokenProperties.getAccessTokenExpiration()))
                    .sign(algorithm);
            return new AccessToken(token);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    @Override
    public RefreshToken generateRefreshToken(UserAuth user, SessionId sessionId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            String token = JWT.create()
                    .withIssuer("arsio")
                    .withSubject(user.getUsername())
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
            String subject = JWT.require(algorithm)
                    .withIssuer("arsio")
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Error while extracting subject ", exception);
        }
    }

    @Override
    public SessionId extractSessionId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            UUID value = UUID.fromString(JWT.require(algorithm)
                            .withIssuer("arsio")
                            .build()
                            .verify(token)
                            .getClaim("session_id").asString());
            return new SessionId(value);
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Error while extracting session_id ", exception);
        }
    }

    private Instant genExpirationDate(Duration expiration) {
        return Instant.now().plus(expiration);
    }
}
