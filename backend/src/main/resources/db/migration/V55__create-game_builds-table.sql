CREATE TABLE game_builds(

    id UUID NOT NULL,

    game_id UUID NOT NULL,

    version VARCHAR(20) NOT NULL,

    object_key VARCHAR(255) NOT NULL,

    file_size INTEGER NOT NULL,

    status VARCHAR(30) NOT NULL,

    published_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_game_builds
        PRIMARY KEY (id),

    CONSTRAINT ck_game_builds_status
        CHECK (
            status IN (
                    'SUPERSEDED',
                    'READY',
                    'PUBLISHED'
                )
            ),

    CONSTRAINT fk_game_builds_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);