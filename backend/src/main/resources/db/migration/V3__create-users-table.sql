CREATE TABLE users (

    id UUID PRIMARY KEY,

    username VARCHAR(50) NOT NULL,

    username_normalized VARCHAR(50) NOT NULL,

    email CITEXT NOT NULL,

    password_hash VARCHAR(255) NOT NULL,

    role user_role NOT NULL DEFAULT 'USER',

    mp_access_token VARCHAR(255),

    profile_image_key VARCHAR(255),

    active BOOLEAN NOT NULL DEFAULT TRUE,

    last_login_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT users_username_normalized_unique
        UNIQUE (username_normalized),

    CONSTRAINT users_email_unique
        UNIQUE (email),

    CONSTRAINT users_username_not_blank_check
        CHECK (char_length(trim(username)) > 0),

    CONSTRAINT users_username_length_check
        CHECK (char_length(trim(username)) BETWEEN 6 AND 50),

    CONSTRAINT users_username_normalized_not_blank_check
        CHECK (char_length(trim(username)) > 0),

    CONSTRAINT users_username_normalized_length_check
        CHECK (char_length(trim(username)) BETWEEN 6 AND 50),

    CONSTRAINT users_email_format_check
        CHECK (position('@' in email) > 1),

    CONSTRAINT users_password_hash_not_blank_check
        CHECK (char_length(trim(password_hash)) > 0),

    CONSTRAINT users_mp_access_token_not_blank_check
        CHECK (
            mp_access_token IS NULL
                OR char_length(trim(mp_access_token)) > 0
            )
);