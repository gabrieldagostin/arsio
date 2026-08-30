package com.arsio.auth.internal.infra.security;

import com.arsio.config.security.CurrentUserMapper;
import com.arsio.user.api.dto.UserAuthenticationData;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.user.api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserFacade userFacade;
    private final CurrentUserMapper currentUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserAuthenticationData user =
                userFacade.findUserAuthenticationDataByUsername(username)
                        .orElseThrow(UserNotFoundException::new);

        return currentUserMapper.toCurrentUser(user);
    }
}
