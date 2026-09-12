ALTER TABLE game_requirements
DROP COLUMN storage_unit;

ALTER TABLE game_requirements
ADD COLUMN storage_unit VARCHAR(2);