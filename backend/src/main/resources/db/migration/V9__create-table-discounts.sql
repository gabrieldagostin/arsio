CREATE TABLE discounts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    game_id UUID NOT NULL,
    percentage DECIMAL(5,2) NOT NULL CHECK (percentage > 0 AND percentage <= 100),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    CHECK (end_date >= start_date),

    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);
