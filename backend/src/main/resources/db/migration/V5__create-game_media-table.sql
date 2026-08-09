CREATE TABLE game_media (

    id UUID NOT NULL,

    game_id UUID NOT NULL,

    object_key TEXT NOT NULL,

    type VARCHAR(30) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_game_media
        PRIMARY KEY (id),

    CONSTRAINT ck_game_media_object_key_not_blank
        CHECK (char_length(trim(object_key)) > 0),

    CONSTRAINT ck_game_media_type
        CHECK (
            type IN (
                'VIDEO',
                'IMAGE'
            )
        ),

    CONSTRAINT fk_game_media_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);