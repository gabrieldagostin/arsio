package com.arsio.notification.internal.infra.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "notifications")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class NotificationEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "user_id",
            nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean read;

    @Column(name = "created_at",
            nullable = false,
            insertable = false,
            updatable = false)
    private Instant createdAt;
}
