package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.PasswordHash;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.valueobject.Username;

public class User {

    private final UserId id;
    private Username username;
    private Email email;
    private PasswordHash passwordHash;
    private UserRole role;
    private boolean active;


    public User(UserId id, Username username, Email email, PasswordHash passwordHash, UserRole role, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
    }

    public static User createUser(
            String username,
            String email,
            String passwordHash
    ) {

        UserId idVo = UserId.generate();
        Username usernameVo = new Username(username);
        String usernameNormalizedVo = new Username(username).getNormalized();
        Email emailVo = new Email(email);
        PasswordHash passwordHashVo = new PasswordHash(passwordHash);
        UserRole role = UserRole.USER;

        return new User(
                idVo,
                usernameVo,
                emailVo,
                passwordHashVo,
                role,
                true
        );
    }

    public UserId getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public PasswordHash getPassword() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public void updatePassword(PasswordHash newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public void updateUsername(Username newUsername) {
        this.username = newUsername;
    }
}
