CREATE TABLE messages (

    id UUID NOT NULL,

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
    
    CONSTRAINT pk_messages
        PRIMARY KEY (id),

    CONSTRAINT ck_messages_content_not_blank
        CHECK (char_length(trim(content)) > 0),

    CONSTRAINT fk_messages_sender_id_users
        FOREIGN KEY (sender_id)
            REFERENCES users(id),

    CONSTRAINT fk_messages_receiver_id_users
        FOREIGN KEY (receiver_id)
            REFERENCES users(id)
);