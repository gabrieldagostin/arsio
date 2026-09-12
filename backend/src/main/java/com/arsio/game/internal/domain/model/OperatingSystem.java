package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public class OperatingSystem {

    private final RequirementId id;
    private Model name;

    public OperatingSystem(RequirementId id, Model name) {
        this.id = id;
        this.name = name;
    }

    public RequirementId getId() {
        return id;
    }

    public Model getName() {
        return name;
    }
}
