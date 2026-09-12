@ApplicationModule(
        displayName = "Game Module",
        allowedDependencies = {
                "config",
                "shared"
        }
)
package com.arsio.game;

import org.springframework.modulith.ApplicationModule;