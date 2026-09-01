package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.model.enums.UserRole;
import com.arsio.user.internal.domain.model.enums.UserStatus;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.PasswordHash;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.valueobject.Username;

import java.util.Locale;

public class User {

    private final UserId id;
    private Username username;
    private Email email;
    private PasswordHash passwordHash;
    private UserRole role;
    private UserStatus status;


    public User(UserId id, Username username, Email email, PasswordHash passwordHash, UserRole role, UserStatus status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
    }

    public static User create(
            String username,
            String email,
            String passwordHash
    ) {

        UserId idVo = UserId.generate();
        Username usernameVo = new Username(username);
        Email emailVo = new Email(email);
        PasswordHash passwordHashVo = new PasswordHash(passwordHash);
        UserRole role = UserRole.USER;
        UserStatus status = UserStatus.ACTIVE;

        return new User(
                idVo,
                usernameVo,
                emailVo,
                passwordHashVo,
                role,
                status
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

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void updatePassword(PasswordHash newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public void updateUsername(Username newUsername) {
        this.username = new Username(newUsername.value().toLowerCase(Locale.ROOT));
    }

    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }
}
