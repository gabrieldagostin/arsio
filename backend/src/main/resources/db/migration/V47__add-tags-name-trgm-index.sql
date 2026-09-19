CREATE INDEX idx_tags_name_lower_trgm
    ON tags USING gin (LOWER(name) gin_trgm_ops);