CREATE TABLE tags(

    id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_tags
        PRIMARY KEY (id),

    CONSTRAINT uk_tags_name
        UNIQUE (name),

    CONSTRAINT ck_tags_name_not_blank
        CHECK (char_length(trim(name)) > 0)
);
