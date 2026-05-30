CREATE TABLE games (

    id UUID PRIMARY KEY,

    developer_id UUID NOT NULL,

    title VARCHAR(150) NOT NULL,

    description TEXT,

    base_price DECIMAL(10,2) NOT NULL,

    status game_status NOT NULL DEFAULT 'DRAFT',

    release_date DATE,

    platform_rate_percentage DECIMAL(3,2) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT games_title_not_blank_check
        CHECK (char_length(trim(title)) > 0),

    CONSTRAINT games_price_not_negative_check
        CHECK (base_price >= 0),

    CONSTRAINT games_release_date_not_past_check
        CHECK (
            release_date IS NULL
            OR release_date <= CURRENT_DATE + INTERVAL '10 years'
            ),

    CONSTRAINT games_platform_rate_percentage_in_interval_check
        CHECK (
            platform_rate_percentage IN (0.5, 0.10, 0.15, 0.20, 0.25)
            ),

    CONSTRAINT games_developer_id_fk
        FOREIGN KEY (developer_id)
            REFERENCES users(id)
);
