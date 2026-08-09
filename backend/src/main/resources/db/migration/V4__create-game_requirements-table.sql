CREATE TABLE game_requirements (

    game_id UUID NOT NULL,

    os TEXT,

    cpu TEXT,

    ram INTEGER,

    gpu TEXT,

    storage INTEGER,
    
    CONSTRAINT pk_game_requirements 
        PRIMARY KEY (game_id),

    CONSTRAINT ck_game_requirements_ram_not_negative
        CHECK (ram > 0),

    CONSTRAINT ck_game_requirements_storage_not_negative
        CHECK (storage > 0),

    CONSTRAINT fk_game_requirements_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);