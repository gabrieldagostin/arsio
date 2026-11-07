package com.arsio.cart.internal.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "gameId")
public class CartItemEmbeddable {

    @Column(name = "game_id", nullable = false)
    private UUID gameId;

    @CreationTimestamp
    @Column(name = "added_at", nullable = false, insertable = false, updatable = false)
    private Instant addedAt;
}
