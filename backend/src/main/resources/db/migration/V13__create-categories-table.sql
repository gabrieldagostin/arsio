CREATE TABLE categories (

    id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,
    
    CONSTRAINT pk_categories
        PRIMARY KEY (id),

    CONSTRAINT uk_categories_name
        UNIQUE (name),

    CONSTRAINT ck_categories_name_not_blank
        CHECK (char_length(trim(name)) > 0)
);