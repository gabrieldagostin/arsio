ALTER TABLE profile
    RENAME CONSTRAINT pk_profile
        TO pk_profiles;

ALTER TABLE profile
    RENAME CONSTRAINT ck_profile_display_name_not_blank
        TO ck_profiles_display_name_not_blank;

ALTER TABLE profile
    RENAME CONSTRAINT fk_profile_user_id_users
        TO fk_profiles_user_id_users;

ALTER TABLE profile
RENAME TO profiles;