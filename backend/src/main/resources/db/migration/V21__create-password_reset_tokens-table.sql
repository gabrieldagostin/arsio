CREATE TABLE password_reset_tokens(

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    token VARCHAR(255) NOT NULL,

    used BOOLEAN NOT NULL DEFAULT FALSE,

    expires_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT password_reset_tokens_token_not_blank_check
        CHECK (char_length(trim(token)) > 0),

    CONSTRAINT password_reset_tokens_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id)

);