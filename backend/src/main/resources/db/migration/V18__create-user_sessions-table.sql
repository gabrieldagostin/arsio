CREATE TABLE user_sessions(

    session_id UUID NOT NULL,

    user_id UUID NOT NULL,

    refresh_token_hash VARCHAR(255) NOT NULL,

    expires_at TIMESTAMPTZ NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT 'FALSE',
    
    CONSTRAINT pk_user_session
        PRIMARY KEY (session_id),

    CONSTRAINT ck_user_sessions_refresh_token_hash_not_blank
        CHECK (char_length(trim(refresh_token_hash)) > 0),

    CONSTRAINT fk_user_sessions_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);