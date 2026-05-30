CREATE TABLE game_media (

    id UUID PRIMARY KEY,

    game_id UUID NOT NULL,

    object_key TEXT NOT NULL,

    type media_type NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT game_media_object_key_not_blank_check
        CHECK (char_length(trim(object_key)) > 0),

    CONSTRAINT game_media_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
