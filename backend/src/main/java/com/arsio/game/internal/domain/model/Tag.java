package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.domain.valueobject.TagName;

public class Tag {

    private final TagId id;
    private TagName name;
    private Boolean active;

    public Tag(TagId id, TagName name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public TagId getId() {
        return id;
    }

    public TagName getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }
}
