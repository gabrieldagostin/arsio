CREATE TABLE wishlist_games (

    wishlist_id UUID NOT NULL,

    game_id UUID NOT NULL,

    added_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_wishlist_games_wishlist
        FOREIGN KEY (wishlist_id) REFERENCES wishlists (id) ON DELETE CASCADE,

    CONSTRAINT pk_wishlist_games
            PRIMARY KEY (wishlist_id, game_id)
);