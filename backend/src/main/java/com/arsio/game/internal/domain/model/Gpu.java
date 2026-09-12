package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public class Gpu {

    private final RequirementId id;
    private Model model;
    private Manufacturer manufacturer;

    public Gpu(RequirementId id, Model model, Manufacturer manufacturer) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public RequirementId getId() {
        return id;
    }

    public Model getModel() {
        return model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
