package com.arsio.auth.internal.infra.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "user_sessions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserSessionEntity {

    @Id
    @Column(nullable = false,  unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAuthEntity user;

    @Column(name = "refresh_token_hash", nullable = false)
    private String refreshTokenHash;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private Boolean revoked;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "created_at", updatable = false, insertable = false, nullable = false)
    private Instant createdAt;
}
