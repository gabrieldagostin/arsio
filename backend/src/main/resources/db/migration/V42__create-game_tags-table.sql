CREATE TABLE game_tags(

    game_id UUID NOT NULL,

    tag_id UUID NOT NULL,

    CONSTRAINT pk_game_tags
        PRIMARY KEY (game_id, tag_id),

    CONSTRAINT fk_game_tags_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id),

    CONSTRAINT fk_game_tags_tag_id_tags
        FOREIGN KEY (tag_id)
            REFERENCES tags(id)
);