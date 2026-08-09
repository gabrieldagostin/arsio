CREATE TABLE user_library (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,

    acquired_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_user_library 
        PRIMARY KEY (id),

    CONSTRAINT uk_user_library_user_id_game_id
        UNIQUE (user_id, game_id),

    CONSTRAINT user_library_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT fk_user_library_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);