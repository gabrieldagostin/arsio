CREATE TABLE genres (

    id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_genres
        PRIMARY KEY (id),

    CONSTRAINT uk_genres_name
        UNIQUE (name),

    CONSTRAINT ck_genres_name_not_blank
        CHECK (char_length(trim(name)) > 0)
);