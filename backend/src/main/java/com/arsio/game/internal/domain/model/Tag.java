package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.domain.valueobject.TagName;

public class Tag {

    private final TagId id;
    private TagName name;
    private boolean active;

    public Tag(TagId id, TagName name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public static Tag create(TagName name) {
        return new Tag(
                TagId.generate(),
                name,
                true
        );
    }

    public TagId getId() {
        return id;
    }

    public TagName getName() {
        return name;
    }

    public boolean idActive() {
        return active;
    }

    public void update(TagName name) {
        this.name = name;
    }

    public void deactivate() {
        this.active = false;
    }
}
