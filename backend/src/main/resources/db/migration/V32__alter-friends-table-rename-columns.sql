ALTER TABLE friends
    RENAME COLUMN user_id TO requester_id;

ALTER TABLE friends
    RENAME COLUMN friend_id TO addressee_id;

ALTER TABLE friends
    RENAME COLUMN user_min TO participant_min;

ALTER TABLE friends
    RENAME COLUMN user_max TO participant_max;

ALTER TABLE friends
    RENAME CONSTRAINT fk_friends_user_id_users
        TO fk_friendships_requester_id_users;

ALTER TABLE friends
    RENAME CONSTRAINT fk_friends_friend_id_users
        TO fk_friendships_addressee_id_users;

ALTER TABLE friends
    RENAME CONSTRAINT ck_friendships_user_not_self
        TO ck_friendships_requester_not_addressee;

ALTER TABLE friends
    RENAME CONSTRAINT uk_friends_friendship
        TO uk_friendships_participants;

ALTER TABLE friends
    RENAME CONSTRAINT pk_friends
        TO pk_friendships;

ALTER TABLE friends
    RENAME TO friendships;