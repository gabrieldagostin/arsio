CREATE TABLE game_requirements (
    game_id UUID PRIMARY KEY,
    os TEXT,
    cpu TEXT,
    ram INTEGER CHECK (ram > 0),
    gpu TEXT,
    storage INTEGER CHECK (storage > 0),

    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);
