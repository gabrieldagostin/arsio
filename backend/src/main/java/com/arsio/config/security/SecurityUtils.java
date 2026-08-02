package com.arsio.config.security;

import com.arsio.shared.valueobject.UserId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    public static UserId getCurrentUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CurrentUser user = (CurrentUser) authentication.getPrincipal();

        return user.getUserId();
    }
}
