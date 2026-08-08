package com.arsio.config.openapi;

import com.arsio.config.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ARS.io API",
                version = "1.0"
        ),
        security = {
                @SecurityRequirement(
                        name = SecurityConfigurations.SECURITY
                )
        }
)
@SecurityScheme(
        name = SecurityConfigurations.SECURITY,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
