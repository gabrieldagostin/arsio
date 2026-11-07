package com.arsio.wishlist.internal.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.*;

@Table(name = "wishlists")
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

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @ElementCollection
    @CollectionTable(
            name = "wishlist_games",
            joinColumns = @JoinColumn(name = "wishlist_id")
    )
    private Set<WishlistGameEmbeddable> games = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
}

