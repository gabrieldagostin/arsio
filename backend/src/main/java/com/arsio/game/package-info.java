@ApplicationModule(
        displayName = "Game Module",
        allowedDependencies = {
                "developer::api",
                "order::api",
                "config",
                "shared"
        }
)
package com.arsio.game;

import org.springframework.modulith.ApplicationModule;