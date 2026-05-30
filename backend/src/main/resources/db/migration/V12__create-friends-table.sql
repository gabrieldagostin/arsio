CREATE TABLE friends (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    friend_id UUID NOT NULL,

    status friend_status NOT NULL,

    user_min UUID GENERATED ALWAYS AS (
        CASE
            WHEN user_id < friend_id THEN user_id
            ELSE friend_id
            END
        ) STORED,

    user_max UUID GENERATED ALWAYS AS (
        CASE
            WHEN user_id > friend_id THEN user_id
            ELSE friend_id
            END
        ) STORED,

    CONSTRAINT friends_friendship_unique
        UNIQUE (user_min, user_max),

    CONSTRAINT friendships_user_not_self_check
        CHECK (user_id <> friend_id),

    CONSTRAINT friends_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT friends_friend_id_fk
        FOREIGN KEY (friend_id)
            REFERENCES users(id)
);
