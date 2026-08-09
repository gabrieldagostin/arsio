package com.arsio.user.internal.infra.persistance.entity;

import com.arsio.user.internal.domain.valueobject.Country;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Table(name = "profile")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity userId;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column
    private String bio;

    @Column(name = "profile_image_key")
    private String profileImageKey;

    @Column(name = "profile_banner_key")
    private String profileBannerKey;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(length = 2)
    private Country country;

    @Column(nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
}
