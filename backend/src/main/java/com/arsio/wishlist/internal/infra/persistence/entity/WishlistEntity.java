package com.arsio.wishlist.internal.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "wishlist")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class WishlistEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ElementCollection
    @CollectionTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "wishlist_id")
    )
    @OrderColumn
    @Column(name = "game_id")
    private List<UUID> gameIds = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
}