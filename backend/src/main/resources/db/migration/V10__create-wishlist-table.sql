CREATE TABLE wishlist (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    game_id UUID NOT NULL,
    
    CONSTRAINT pk_wishlist
        PRIMARY KEY (id),

    CONSTRAINT uk_wishlist_user_id_game_id
        UNIQUE (user_id, game_id),

    CONSTRAINT wishlist_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT fk_wishlist_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);