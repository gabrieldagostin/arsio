package com.arsio.chat.internal.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "messages")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class MessageEntity {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "sender_id", nullable = false, updatable = false)
    private UUID senderId;

    @Column(name = "receiver_id", nullable = false, updatable = false)
    private UUID receiverId;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", updatable = false, insertable = false, nullable = false)
    private Instant createdAt;
}
