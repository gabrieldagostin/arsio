package com.arsio.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public final class SecurityUtils {

    public static UUID getCurrentUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CurrentUser user = (CurrentUser) authentication.getPrincipal();

        return user.getUserId();
    }
}
