CREATE TABLE categories (

    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT categories_name_unique
        UNIQUE (name),

    CONSTRAINT categories_name_not_blank_check
        CHECK (char_length(trim(name)) > 0)
);
