CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) UNIQUE NOT NULL CHECK (trim(username) <> '') ,
    email CITEXT UNIQUE NOT NULL CHECK (position('@' in email) > 1),
    password TEXT NOT NULL,
    role user_role NOT NULL DEFAULT 'USER',
    mp_access_token VARCHAR(255) CHECK (mp_access_token IS NULL OR trim(mp_access_token) <> ''),
    profile_image_key VARCHAR(255),
    last_login_at TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
