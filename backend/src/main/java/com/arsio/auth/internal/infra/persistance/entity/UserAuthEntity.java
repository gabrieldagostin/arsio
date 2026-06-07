package com.arsio.auth.internal.infra.persistance.entity;

import com.arsio.user.internal.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserAuthEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(columnDefinition = "citext", nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "profile_image_key")
    private String profileImageKey;

}
