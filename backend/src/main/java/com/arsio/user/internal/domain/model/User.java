package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Password;
import com.arsio.shared.valueobject.UserId;
import com.arsio.user.internal.domain.valueobject.Username;

public class User {

    private final UserId id;
    private Username username;
    private String usernameNormalized;
    private Email email;
    private Password password;
    private UserRole role;
    private String profileImageKey;
    private boolean active;


    public User(UserId id, Username username, String usernameNormalized, Email email, Password password, UserRole role, String profileImageKey, boolean active) {
        this.id = id;
        this.username = username;
        this.usernameNormalized = usernameNormalized;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profileImageKey = profileImageKey;
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
        Password passwordVo = new Password(passwordHash);
        UserRole role = UserRole.USER;

        return new User(
                idVo,
                usernameVo,
                usernameNormalizedVo,
                emailVo,
                passwordVo,
                role,
                null,
                true
        );
    }

    public UserId getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public String getUsernameNormalized() {
        return usernameNormalized;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getProfileImageKey() {
        return profileImageKey;
    }

    public boolean isActive() {
        return active;
    }

    public void updatePassword(Password newPassword) {
        this.password = newPassword;
    }
}
