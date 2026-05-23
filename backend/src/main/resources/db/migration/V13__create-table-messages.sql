CREATE TABLE messages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sender_id UUID NOT NULL,
    receiver_id UUID NOT NULL,

    user_min UUID GENERATED ALWAYS AS (
        CASE
            WHEN sender_id < receiver_id THEN sender_id
            ELSE receiver_id
            END
        ) STORED,

    user_max UUID GENERATED ALWAYS AS (
        CASE
            WHEN sender_id > receiver_id THEN sender_id
            ELSE receiver_id
            END
        ) STORED,

    content TEXT NOT NULL CHECK (trim(content) <> ''),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);
