CREATE TABLE game_categories (

    game_id UUID NOT NULL,

    category_id UUID NOT NULL,

    CONSTRAINT game_categories_game_id_category_id_primary_key
        PRIMARY KEY (game_id, category_id),

    CONSTRAINT game_categories_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id),

    CONSTRAINT game_categories_category_id_fk
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
);
