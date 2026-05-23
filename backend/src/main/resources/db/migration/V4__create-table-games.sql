CREATE TABLE games (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    developer_id UUID NOT NULL,
    title VARCHAR(150) NOT NULL CHECK (trim(title) <> ''),
    description TEXT,
    base_price DECIMAL(10,2) NOT NULL CHECK (base_price >= 0),
    status game_status NOT NULL DEFAULT 'DRAFT',
    release_date DATE CHECK (release_date IS NULL OR release_date <= CURRENT_DATE + INTERVAL '10 years'),
    platform_rate_percentage DECIMAL(3,2) NOT NULL CHECK (platform_rate_percentage IN (0.02, 0.05, 0.10, 0.15, 0.20, 0.25)),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (developer_id) REFERENCES users(id) ON DELETE CASCADE
);
