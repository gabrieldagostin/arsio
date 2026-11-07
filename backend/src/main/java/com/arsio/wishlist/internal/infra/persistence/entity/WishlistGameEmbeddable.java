package com.arsio.wishlist.internal.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "gameId")
public class WishlistGameEmbeddable {

    @Column(name = "game_id", nullable = false)
    private UUID gameId;

    @CreationTimestamp
    @Column(name = "added_at", nullable = false, insertable = false, updatable = false)
    private Instant addedAt;
}
