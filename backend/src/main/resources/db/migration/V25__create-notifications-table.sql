CREATE TABLE notifications(

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    title VARCHAR(255) NOT NULL,

    message TEXT NOT NULL,

    read BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_notifications
        PRIMARY KEY (id),

    CONSTRAINT ck_notifications_title_not_blank
        CHECK (char_length(trim(title)) > 0),

    CONSTRAINT ck_notifications_message_not_blank
        CHECK (char_length(trim(message)) > 0),

    CONSTRAINT fk_notifications_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);