package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;

public class Gpu {

    private final RequirementId id;
    private Manufacturer manufacturer;
    private Model model;
    private boolean active;

    public Gpu(RequirementId id, Manufacturer manufacturer, Model model, boolean active) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.active = active;
    }

    public static Gpu create(Manufacturer manufacturer, Model model) {
        return new Gpu(
                RequirementId.generate(),
                manufacturer,
                model,
                true
        );
    }

    public RequirementId getId() {
        return id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public boolean isActive() {
        return active;
    }

    public void update(Manufacturer manufacturer, Model model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public void deactivate() {
        this.active = false;
    }
}
