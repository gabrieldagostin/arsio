package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public class Processor {

    private final RequirementId id;
    private Model model;
    private Manufacturer manufacturer;
    private boolean active;

    public Processor(RequirementId id, Model model, Manufacturer manufacturer, boolean active) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.active = active;
    }

    public static Processor create(Model model, Manufacturer manufacturer) {

        return new Processor(
                RequirementId.generate(),
                model,
                manufacturer,
                true
        );
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

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}
