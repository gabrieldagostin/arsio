CREATE TABLE user_sessions(

    session_id UUID PRIMARY KEY NOT NULL,

    user_id UUID NOT NULL,

    refresh_token_hash VARCHAR(255) NOT NULL,

    expires_at TIMESTAMPTZ NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT 'FALSE',

    CONSTRAINT user_sessions_refresh_token_hash_not_blank_check
        CHECK (char_length(trim(refresh_token_hash)) > 0),

    CONSTRAINT user_sessions_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);