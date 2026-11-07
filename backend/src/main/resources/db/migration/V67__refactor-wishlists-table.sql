DROP TABLE IF EXISTS wishlist_games CASCADE;
DROP TABLE IF EXISTS wishlists CASCADE;
DROP TABLE IF EXISTS wishlist CASCADE;

CREATE TABLE wishlists (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_wishlists PRIMARY KEY (id),

    CONSTRAINT uk_wishlists_user_id UNIQUE (user_id),

    CONSTRAINT fk_wishlists_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE wishlist_games (

    wishlist_id UUID NOT NULL,

    game_id UUID NOT NULL,

    added_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),


    CONSTRAINT pk_wishlist_games PRIMARY KEY (wishlist_id, game_id),

    CONSTRAINT fk_wishlist_games_wishlist
        FOREIGN KEY (wishlist_id) REFERENCES wishlists(id) ON DELETE CASCADE
);