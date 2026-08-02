package com.arsio.config.security;

import com.arsio.shared.valueobject.UserId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CurrentUser implements UserDetails {

    private final UserId userId;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public CurrentUser(
            UserId userId,
            String email,
            Collection<? extends GrantedAuthority> authorities) {

        this.userId = userId;
        this.email = email;
        this.authorities = authorities;
    }

    public UserId getUserId() {
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
