package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidRequirementCategoryException;

public enum RequirementCategory {

    MINIMUM(0, "Minimum"),
    RECOMMENDED(1, "Recommended"),;

    private final Integer cod;
    private final String description;

    RequirementCategory(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public RequirementCategory toEnum() {
        if (cod == null) return null;
        for (RequirementCategory requirementCategory : RequirementCategory.values()) {
            if (cod.equals(requirementCategory.cod)) return requirementCategory;
        }
        throw new InvalidRequirementCategoryException();
    }
}
