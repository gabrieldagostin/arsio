CREATE TABLE discounts (

    id UUID NOT NULL,

    game_id UUID NOT NULL,

    percentage DECIMAL(5,2) NOT NULL,

    start_date DATE NOT NULL,

    end_date DATE NOT NULL,
    
    CONSTRAINT pk_discounts 
        PRIMARY KEY (id),

    CONSTRAINT ck_discounts_percentage_in_interval
        CHECK (percentage > 0 AND percentage <= 100),

    CONSTRAINT ck_discounts_start_date_minor_end_date
        CHECK (end_date >= start_date),

    CONSTRAINT fk_discounts_game_id_games
        FOREIGN KEY (game_id)
            REFERENCES games(id)
);