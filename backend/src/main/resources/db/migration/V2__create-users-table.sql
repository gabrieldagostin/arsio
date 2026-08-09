CREATE TABLE users (

    id UUID NOT NULL,

    username VARCHAR(30) NOT NULL,

    email CITEXT NOT NULL,

    password_hash VARCHAR(255) NOT NULL,

    role VARCHAR(30) NOT NULL DEFAULT 'USER',

    mp_access_token VARCHAR(255),

    active BOOLEAN NOT NULL DEFAULT TRUE,

    last_login_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_users
        PRIMARY KEY (id),

    CONSTRAINT uk_users_username
        UNIQUE (username),

    CONSTRAINT uk_users_email
        UNIQUE (email),

    CONSTRAINT ck_users_username_not_blank
        CHECK (char_length(trim(username)) > 0),

    CONSTRAINT ck_users_username_length
        CHECK (char_length(trim(username)) BETWEEN 6 AND 50),

    CONSTRAINT ck_users_email_format
        CHECK (position('@' in email) > 1),

    CONSTRAINT ck_users_password_hash_not_blank
        CHECK (char_length(trim(password_hash)) > 0),

    CONSTRAINT ck_users_role
        CHECK (
            role IN (
                'ADMIN',
                'USER',
                'DEV'
            )
        ),

    CONSTRAINT ck_users_mp_access_token_not_blank
        CHECK (
            mp_access_token IS NULL
                OR char_length(trim(mp_access_token)) > 0
            )
);