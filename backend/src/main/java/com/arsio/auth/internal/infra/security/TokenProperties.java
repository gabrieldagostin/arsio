package com.arsio.auth.internal.infra.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "api.security.token")
@Getter
@Setter
public class TokenProperties {

    private String secret;
    private Duration accessTokenExpiration;
    private Duration refreshTokenExpiration;
}
