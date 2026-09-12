package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.domain.valueobject.GenreName;

public class Genre {

    private final GenreId id;
    private GenreName name;
    private Boolean active;

    public Genre(GenreId id, GenreName name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public GenreId getId() {
        return id;
    }

    public GenreName getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }
}
