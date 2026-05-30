CREATE TABLE discounts (

    id UUID PRIMARY KEY,

    game_id UUID NOT NULL,

    percentage DECIMAL(5,2) NOT NULL,

    start_date DATE NOT NULL,

    end_date DATE NOT NULL,

    CONSTRAINT discounts_percentage_in_interval_check
        CHECK (percentage > 0 AND percentage <= 100),

    CONSTRAINT discounts_start_date_minor_end_date_check
        CHECK (end_date >= start_date),

    CONSTRAINT discounts_game_id_fk
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);
