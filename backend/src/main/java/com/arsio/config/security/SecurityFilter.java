package com.arsio.config.security;

import com.arsio.auth.api.facade.AuthFacade;
import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.api.facade.UserFacade;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthFacade authFacade;
    private final UserFacade userFacade;
    private final CurrentUserMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            try {
                var subject = authFacade.extractSubject(token);

                UserAuthenticationData userAuthenticationData = userFacade.findUserAuthenticationDataByUsername(subject)
                        .orElseThrow(() -> new UserNotFoundException(subject));

                CurrentUser currentUser = mapper.toCurrentUser(userAuthenticationData);

                var authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (TokenExpiredException exception) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println("shouldNotFilter -> path: " + request.getRequestURI());
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.startsWith("/auth/login")
                || path.startsWith("/auth/register")
                || path.startsWith("/auth/refresh");
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
