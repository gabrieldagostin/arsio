CREATE TABLE wishlist (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,

    CONSTRAINT wishlist_user_id_game_id_unique
        UNIQUE (user_id, game_id),

    CONSTRAINT wishlist_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT wishlist_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
