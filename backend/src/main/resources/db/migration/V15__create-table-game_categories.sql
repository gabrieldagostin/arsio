CREATE TABLE game_categories (
    game_id UUID NOT NULL,
    category_id UUID NOT NULL,

    PRIMARY KEY (game_id, category_id),

    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);
