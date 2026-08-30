package com.arsio.config.security;

import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.internal.domain.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentUserMapper {

    public CurrentUser toCurrentUser(UserAuthenticationData user) {

        return new CurrentUser(
                user.id(),
                user.username(),
                user.email(),
                user.passwordHash(),
                getAuthorities(user.role())
        );
    }

    private List<GrantedAuthority> getAuthorities(UserRole role) {

        if (role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        if (role == UserRole.DEV) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_DEV"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }
}