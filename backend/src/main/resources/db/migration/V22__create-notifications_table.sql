CREATE TABLE notifications(

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    title VARCHAR(255) NOT NULL,

    message TEXT NOT NULL,

    read BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT notifications_title_not_blank_check
        CHECK (char_length(trim(title)) > 0),

    CONSTRAINT notifications_message_not_blank_check
        CHECK (char_length(trim(message)) > 0),

    CONSTRAINT notifications_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);