@ApplicationModule(
        displayName = "Authentication Module",
        allowedDependencies = {
                "config",
                "shared"
        }
)
package com.arsio.auth;

import org.springframework.modulith.ApplicationModule;