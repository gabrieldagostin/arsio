CREATE TABLE messages (

    id UUID PRIMARY KEY,

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

    content TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT messages_content_not_blank_check
        CHECK (char_length(trim(content)) > 0),

    CONSTRAINT messages_sender_id_fk
        FOREIGN KEY (sender_id)
            REFERENCES users(id),

    CONSTRAINT messages_receiver_id_fk
        FOREIGN KEY (receiver_id)
            REFERENCES users(id)
);
