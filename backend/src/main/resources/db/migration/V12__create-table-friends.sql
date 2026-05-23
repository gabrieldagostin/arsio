CREATE TABLE friends (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    friend_id UUID NOT NULL,
    status friend_status NOT NULL,

    user_min UUID GENERATED ALWAYS AS (
        CASE
            WHEN user_id < friend_id THEN user_id
            ELSE friend_id
            END
        ) STORED,

    user_max UUID GENERATED ALWAYS AS (
        CASE
            WHEN user_id > friend_id THEN user_id
            ELSE friend_id
            END
        ) STORED,

    CHECK (user_id <> friend_id),

    CONSTRAINT unique_friendship UNIQUE (user_min, user_max),

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users(id) ON DELETE CASCADE
);
