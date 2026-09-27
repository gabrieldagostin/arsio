package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.GenreId;
import com.arsio.game.internal.domain.valueobject.GenreName;

public class Genre {

    private final GenreId id;
    private GenreName name;
    private boolean active;

    public Genre(GenreId id, GenreName name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public static Genre create(GenreName name) {
        return new Genre(
                GenreId.generate(),
                name,
                true
        );
    }

    public GenreId getId() {
        return id;
    }

    public GenreName getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void update(GenreName name) {
        this.name = name;
    }
}
