package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public class OperatingSystem {

    private final RequirementId id;
    private Model name;
    private boolean active;

    public OperatingSystem(RequirementId id, Model name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public RequirementId getId() {
        return id;
    }

    public Model getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
