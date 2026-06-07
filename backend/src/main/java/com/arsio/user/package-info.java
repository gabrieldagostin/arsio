@ApplicationModule(
        displayName = "User Module",
        allowedDependencies = {
                "config",
                "shared"
        }
)
package com.arsio.user;

import org.springframework.modulith.ApplicationModule;