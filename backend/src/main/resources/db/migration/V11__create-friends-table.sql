CREATE TABLE friends (

    id UUID NOT NULL,

    user_id UUID NOT NULL,

    friend_id UUID NOT NULL,

    status VARCHAR(30) NOT NULL,

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

    CONSTRAINT pk_friends
        PRIMARY KEY (id),

    CONSTRAINT uk_friends_friendship
        UNIQUE (user_min, user_max),

    CONSTRAINT ck_friendships_user_not_self
        CHECK (user_id <> friend_id),

    CONSTRAINT ck_friends_status
        CHECK (
            status IN (
                'PENDING', 
                'ACCEPTED'
            )
        ),

    CONSTRAINT fk_friends_user_id_users
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT fk_friends_friend_id_users
        FOREIGN KEY (friend_id)
            REFERENCES users(id)
);