CREATE INDEX idx_users_username_lower_trgm
    ON users USING gin (LOWER(username) gin_trgm_ops);