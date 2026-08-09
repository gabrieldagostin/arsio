CREATE TABLE reviews (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,

    rating INT NOT NULL,

    comment TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_reviews
        PRIMARY KEY (id),

    CONSTRAINT uk_reviews_user_id_game_id
        UNIQUE (user_id, game_id),

    CONSTRAINT ck_reviews_rating_length
        CHECK (rating BETWEEN 1 AND 5),

    CONSTRAINT fk_reviews_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT fk_reviews_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);