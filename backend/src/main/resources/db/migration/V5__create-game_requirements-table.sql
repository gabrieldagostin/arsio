CREATE TABLE game_requirements (

    game_id UUID PRIMARY KEY,

    os TEXT,

    cpu TEXT,

    ram INTEGER,

    gpu TEXT,

    storage INTEGER,

    CONSTRAINT game_requirements_ram_not_negative_check
        CHECK (ram > 0),

    CONSTRAINT game_requirements_storage_not_negative_check
        CHECK (storage > 0),

    CONSTRAINT game_requirements_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
