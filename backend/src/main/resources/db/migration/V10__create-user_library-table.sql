CREATE TABLE user_library (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,

    acquired_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT user_library_user_id_game_id_unique
        UNIQUE (user_id, game_id),

    CONSTRAINT user_library_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT user_library_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
