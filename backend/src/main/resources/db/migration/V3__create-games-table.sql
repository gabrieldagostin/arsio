CREATE TABLE games (

    id UUID NOT NULL,

    developer_id UUID NOT NULL,

    title VARCHAR(150) NOT NULL,

    description TEXT,

    base_price DECIMAL(10,2) NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',

    release_date DATE,

    platform_rate_percentage DECIMAL(3,2) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_games 
        PRIMARY KEY (id),

    CONSTRAINT ck_games_title_not_blank
        CHECK (char_length(trim(title)) > 0),

    CONSTRAINT ck_games_base_price_positive
        CHECK (base_price >= 0),

    CONSTRAINT ck_games_status
        CHECK (
            status IN (
                'PUBLISHED', 
                'DRAFT', 
                'ARCHIVED'
            )
        ),

    CONSTRAINT ck_games_release_date_not_past
        CHECK (
            release_date IS NULL
            OR release_date <= CURRENT_DATE + INTERVAL '10 years'
            ),

    CONSTRAINT ck_games_platform_rate_percentage_in_interval
        CHECK (
            platform_rate_percentage IN (0.5, 0.10, 0.15, 0.20, 0.25)
            ),

    CONSTRAINT fk_games_developer_id_users
        FOREIGN KEY (developer_id)
            REFERENCES users(id)
            ON DELETE RESTRICT
);