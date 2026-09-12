package com.arsio.user.internal.infra.persistence.entity;

import com.arsio.user.internal.domain.valueobject.Country;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Table(name = "profiles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProfileEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column
    private String bio;

    @Column(name = "avatar_object_key")
    private String avatarObjectKey;

    @Column(name = "banner_object_key")
    private String bannerObjectKey;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(length = 2)
    private Country country;

    @Column(nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
}
