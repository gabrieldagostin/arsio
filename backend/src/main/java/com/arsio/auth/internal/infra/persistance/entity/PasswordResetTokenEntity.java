package com.arsio.auth.internal.infra.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "password_reset_tokens")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PasswordResetTokenEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAuthEntity user;

    @Column(name = "token",
            nullable = false)
    private String passwordToken;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE",
            nullable = false)
    private boolean used;

    @Column(name = "expires_at",
            updatable = false,
            nullable = false)
    private Instant expiresAt;

    @Column(name = "created_at",
            updatable = false,
            insertable = false,
            nullable = false)
    private Instant createdAt;
}
