package com.arsio.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class CurrentUser implements UserDetails {

    private final UUID userId;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public CurrentUser(
            UUID userId,
            String email,
            Collection<? extends GrantedAuthority> authorities) {

        this.userId = userId;
        this.email = email;
        this.authorities = authorities;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override public boolean isAccountNonExpired() { return true; }

    @Override public boolean isAccountNonLocked() { return true; }

    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override public boolean isEnabled() { return true; }
}
