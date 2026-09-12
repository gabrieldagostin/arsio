CREATE TABLE game_genres (

    game_id UUID NOT NULL,

    genre_id UUID NOT NULL,

    CONSTRAINT pk_game_genres
        PRIMARY KEY (game_id, genre_id),

    CONSTRAINT fk_game_genres_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id),

    CONSTRAINT fk_game_genres_genre_id_genres
        FOREIGN KEY (genre_id)
            REFERENCES genres(id)
);