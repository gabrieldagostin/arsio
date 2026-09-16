CREATE INDEX idx_genres_name_lower_trgm
    ON genres USING gin (LOWER(name) gin_trgm_ops);