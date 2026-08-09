CREATE TABLE game_categories (

    game_id UUID NOT NULL,

    category_id UUID NOT NULL,
    
    CONSTRAINT pk_game_categories
        PRIMARY KEY (game_id, category_id),

    CONSTRAINT fk_game_categories_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id),

    CONSTRAINT fk_game_categories_category_id_categories
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
);