CREATE TABLE operating_systems (

    id UUID NOT NULL,

    name VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_operating_systems
        PRIMARY KEY (id),

    CONSTRAINT uk_operating_systems_name
        UNIQUE (name),

    CONSTRAINT ck_operating_systems_name_not_blank
        CHECK (char_length(trim(name)) > 0)
);