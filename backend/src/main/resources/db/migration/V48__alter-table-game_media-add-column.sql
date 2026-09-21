ALTER TABLE game_media
ADD COLUMN role VARCHAR(30) NOT NULL;

ALTER TABLE game_media
ADD CONSTRAINT ck_game_media_role
    CHECK (
        role IN (
                 'THUMBNAIL',
                 'BANNER',
                 'SCREENSHOT',
                 'TRAILER'
            )
        );