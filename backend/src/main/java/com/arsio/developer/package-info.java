@ApplicationModule(
        displayName = "Developer Module",
        allowedDependencies = {
                "config",
                "shared"
        }
)
package com.arsio.developer;

import org.springframework.modulith.ApplicationModule;