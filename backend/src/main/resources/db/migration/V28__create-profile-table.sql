CREATE TABLE profile (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    display_name VARCHAR(30) NOT NULL,

    bio TEXT,

    profile_image_key VARCHAR(255),

    profile_banner_key VARCHAR(255),

    country VARCHAR(50),

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_profile
        PRIMARY KEY (id),

    CONSTRAINT ck_profile_display_name_not_blank
        CHECK (char_length(trim(display_name)) > 0),

    CONSTRAINT fk_profile_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);