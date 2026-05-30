package com.arsio.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.security.token")
@Getter
@Setter
public class TokenProperties {

    private String secret;
}
