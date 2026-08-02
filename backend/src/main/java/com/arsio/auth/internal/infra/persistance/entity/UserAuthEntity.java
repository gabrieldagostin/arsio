package com.arsio.auth.internal.infra.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "username_normalized", nullable = false, unique = true)
    private String usernameNormalized;

    @Column(columnDefinition = "citext", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "profile_image_key")
    private String profileImageKey;
}
