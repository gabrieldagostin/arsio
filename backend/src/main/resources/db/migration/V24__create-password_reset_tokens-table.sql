CREATE TABLE password_reset_tokens(

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    token VARCHAR(255) NOT NULL,

    used BOOLEAN NOT NULL DEFAULT FALSE,

    expires_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_password_reset_token
        PRIMARY KEY (id),

    CONSTRAINT ck_password_reset_tokens_token_not_blank
        CHECK (char_length(trim(token)) > 0),

    CONSTRAINT fk_password_reset_tokens_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);