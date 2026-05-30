CREATE TABLE reviews (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,

    rating INT NOT NULL,

    comment TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT reviews_rating_length_check
        CHECK (rating BETWEEN 1 AND 5),

    CONSTRAINT reviews_user_id_game_id_unique
        UNIQUE (user_id, game_id),

    CONSTRAINT reviews_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT reviews_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
