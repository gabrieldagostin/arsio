ALTER TABLE game_media
ADD COLUMN external_url TEXT;

ALTER TABLE game_media
ADD CONSTRAINT ck_game_media_storage
CHECK (
    (type = 'IMAGE' AND object_key IS NOT NULL AND external_url IS NULL)
    OR
    (type = 'VIDEO' AND role = 'TRAILER' AND object_key IS NULL AND external_url IS NOT NULL)
);